package com.bkonecsni.soccerbet.controllers;

import com.bkonecsni.soccerbet.domain.entities.Bet;
import com.bkonecsni.soccerbet.services.bet.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class BetController {

    @Autowired
    private BetService betService;

    @RequestMapping("/bet/list")
    public List<Bet> listBets(String userId){
        return betService.listBets(userId);
    }

    @RequestMapping("/bet/create")
    public Map<String, Boolean> create(String userId, String matchIds, String homeGoals, String awayGoals) {
        List<Long> matchIdList = createMatchIdList(matchIds);
        List<Integer> homeTeamGoalsList = createGoalsList(homeGoals);
        List<Integer> awayTeamGoalsList = createGoalsList(awayGoals);

        if (listAreSameSize(matchIdList, homeTeamGoalsList, awayTeamGoalsList)) {
            return betService.persistBets(userId, matchIdList, homeTeamGoalsList, awayTeamGoalsList);
        }

        Map<String, Boolean> unSuccessFullMap = new HashMap();
        unSuccessFullMap.put("success", false);
        return unSuccessFullMap;
    }

    private boolean listAreSameSize(List<Long> matchIdList, List<Integer> homeTeamGoalsList, List<Integer> awayTeamGoalsList) {
        int homeTeamGoalsListSize = homeTeamGoalsList.size();
        int awayTeamGoalsListSize = awayTeamGoalsList.size();
        int matchIdListSize = matchIdList.size();

        return homeTeamGoalsListSize == awayTeamGoalsListSize && homeTeamGoalsListSize == matchIdListSize;
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