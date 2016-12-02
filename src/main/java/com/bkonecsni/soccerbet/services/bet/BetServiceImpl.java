package com.bkonecsni.soccerbet.services.bet;

import com.bkonecsni.soccerbet.domain.MatchResult;
import com.bkonecsni.soccerbet.domain.entities.Bet;
import com.bkonecsni.soccerbet.domain.entities.Match;
import com.bkonecsni.soccerbet.domain.entities.User;
import com.bkonecsni.soccerbet.repositories.BetRepository;
import com.bkonecsni.soccerbet.repositories.MatchRepository;
import com.bkonecsni.soccerbet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BetServiceImpl implements BetService{

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public List<Bet> listBets(String userId) {
        return betRepository.findByUserId(userId);
    }

    @Override
    public Map<String, Boolean> createBet(String userId, List<Long> matchIdList, List<Integer> homeTeamGoalsList, List<Integer> awayTeamGoalsList) {
        Map<String, Boolean> success = new HashMap<>();
        try {
            User user = userRepository.findOne(userId);
            for (int i=0; i<matchIdList.size(); i++) {
                saveOrUpdateBet(matchIdList, homeTeamGoalsList, awayTeamGoalsList, user, i);
            }

            success.put("success", true);
        }
        catch (Exception ex) {
            success.put("success", false);
        }

        return success;
    }

    private void saveOrUpdateBet(List<Long> matchIdList, List<Integer> homeTeamGoalsList, List<Integer> awayTeamGoalsList, User user, int i) {
        Match match = matchRepository.findOne(matchIdList.get(i));
        Bet existingBet = betRepository.findByUserAndMatch(user, match);

        Integer homeTeamGoals = homeTeamGoalsList.get(i);
        Integer awayTeamGoals = awayTeamGoalsList.get(i);
        if (existingBet != null) {
            setExistingBetFieldsAndUpdate(existingBet, homeTeamGoals, awayTeamGoals);
        } else {
            Bet bet = new Bet(user, match, homeTeamGoals, awayTeamGoals);
            betRepository.save(bet);
        }
    }

    private void setExistingBetFieldsAndUpdate(Bet existingBet, Integer homeTeamGoals, Integer awayTeamGoals) {
        existingBet.setHomeTeamGoals(homeTeamGoals);
        existingBet.setAwayTeamGoals(awayTeamGoals);
        existingBet.setMatchResult(MatchResult.calculateMatchResult(homeTeamGoals, awayTeamGoals));
        betRepository.save(existingBet);
    }
}