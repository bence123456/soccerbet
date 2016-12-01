package com.bkonecsni.soccerbet.controllers;

import com.bkonecsni.soccerbet.domain.Bet;
import com.bkonecsni.soccerbet.services.bet.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class BetController {

    @Autowired
    private BetService betService;

    @RequestMapping("/bet/list")
    @ResponseBody
    public List<Bet> listBets(String userId){
        return betService.listBets(userId);
    }

    @RequestMapping("/bet/create")
    @ResponseBody
    public Map<String, Boolean> create(String userId, String matchIds, String homeGoals, String awayGoals) {
        List<Long> matchIdList = createMatchIdList(matchIds);
        List<Integer> homeTeamGoalsList = createGoalsList(homeGoals);
        List<Integer> awayTeamGoalsList = createGoalsList(awayGoals);

        return betService.createBet(userId, matchIdList, homeTeamGoalsList, awayTeamGoalsList);
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