package com.bkonecsni.soccerbet.common.service;

import com.bkonecsni.soccerbet.domain.DBTeam;
import com.bkonecsni.soccerbet.domain.Match;
import com.bkonecsni.soccerbet.domain.User;
import com.bkonecsni.soccerbet.football.data.api.FootballDataService;

public interface CommonService {

    Long getIdFromUrl(String url);

    FootballDataService getFootballDataService();

    DBTeam findTeamById(Long id);

    User findUserById(String id);

    Match findMatchById(Long id);

    void calculateAndSavePoints(Match match);
}
