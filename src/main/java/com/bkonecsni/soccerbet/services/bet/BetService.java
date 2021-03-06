package com.bkonecsni.soccerbet.services.bet;

import com.bkonecsni.soccerbet.domain.entities.Bet;

import java.util.List;
import java.util.Map;

public interface BetService {

    List<Bet> listBets(String userId);

    Map<String, Boolean> persistBets(String userId, List<Long> matchIdList, List<Integer> homeTeamGoalsList, List<Integer> awayTeamGoalsList);
}