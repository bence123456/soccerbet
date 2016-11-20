package com.bkonecsni.soccerbet.controllers;

import com.bkonecsni.soccerbet.common.service.CommonService;
import com.bkonecsni.soccerbet.domain.Bet;
import com.bkonecsni.soccerbet.domain.Match;
import com.bkonecsni.soccerbet.domain.User;
import com.bkonecsni.soccerbet.repositories.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class BetController {

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private CommonService commonService;

    @RequestMapping("/bet/list")
    @ResponseBody
    public List<Bet> loadBets(String userId){
        return betRepository.findByUserId(userId);
    }

    @RequestMapping("/bet/create")
    @ResponseBody
    public Map<String, Boolean> create(String userId, String matchIds, String homeGoals, String awayGoals) {
        List<Long> matchIdList = createMatchIdList(matchIds);
        List<Integer> homeTeamGoalsList = createGoalsList(homeGoals);
        List<Integer> awayTeamGoalsList = createGoalsList(awayGoals);

        Map<String, Boolean> success = new HashMap<>();
        try {
            User user = commonService.findUserById(userId);
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
        Match match = commonService.findMatchById(matchIdList.get(i));
        Bet existingBet = betRepository.findByUserAndMatch(user, match);

        Integer homeTeamGoals = homeTeamGoalsList.get(i);
        Integer awayTeamGoals = awayTeamGoalsList.get(i);
        if (existingBet != null) {
            existingBet.setHomeTeamGoals(homeTeamGoals);
            existingBet.setAwayTeamGoals(awayTeamGoals);
            existingBet.setMatchResult(existingBet.calculateMatchResult(homeTeamGoals, awayTeamGoals));
            betRepository.save(existingBet);
        } else {
            Bet bet = new Bet(user, match, homeTeamGoals, awayTeamGoals);
            betRepository.save(bet);
        }
    }

    private List<Long> createMatchIdList(String matchIds) {
        List<String> matchIdStringList = Arrays.asList(matchIds.split(","));
        List<Long> matchIdLongList = new ArrayList<>();

        for (String matchId : matchIdStringList) {
            matchIdLongList.add(Long.valueOf(matchId));
        }

        return matchIdLongList;
    }

    private List<Integer> createGoalsList(String goals) {
        List<String> goalsStringList = Arrays.asList(goals.split(","));
        List<Integer> goalsIntegerList = new ArrayList<>();

        for (String goal : goalsStringList) {
            goalsIntegerList.add(Integer.valueOf(goal));
        }

        return goalsIntegerList;
    }
}