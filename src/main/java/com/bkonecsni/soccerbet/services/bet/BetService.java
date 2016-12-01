package com.bkonecsni.soccerbet.services.bet;

import com.bkonecsni.soccerbet.domain.Bet;

import java.util.List;
import java.util.Map;

public interface BetService {

    List<Bet> listBets(String userId);

    Map<String, Boolean> createBet(String userId, List<Long> matchIdList, List<Integer> homeTeamGoalsList, List<Integer> awayTeamGoalsList);
}