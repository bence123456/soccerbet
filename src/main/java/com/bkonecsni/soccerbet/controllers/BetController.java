package com.bkonecsni.soccerbet.controllers;

import com.bkonecsni.soccerbet.common.CommonService;
import com.bkonecsni.soccerbet.domain.Bet;
import com.bkonecsni.soccerbet.domain.Match;
import com.bkonecsni.soccerbet.domain.User;
import com.bkonecsni.soccerbet.repositories.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class BetController {

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private CommonService commonService;

    @RequestMapping("/bet/create")
    public String create(String userId, String matchIds, String homeGoals, String awayGoals) {
        List<Long> matchIdList = createMatchIdList(matchIds);
        List<Integer> homeTeamGoalsList = createGoalsList(homeGoals);
        List<Integer> awayTeamGoalsList = createGoalsList(awayGoals);

        try {
            User user = commonService.findUserById(userId);

            for (int i=0; i<matchIdList.size(); i++) {
                Match match = commonService.findMatchById(matchIdList.get(i));
                Bet bet = new Bet(user, match, homeTeamGoalsList.get(i), awayTeamGoalsList.get(i));
                betRepository.save(bet);
            }
        }
        catch (Exception ex) {
            ex.toString();
        }
        return "save";
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