package com.bkonecsni.soccerbet.common;

import com.bkonecsni.soccerbet.domain.Match;
import com.bkonecsni.soccerbet.domain.User;
import com.bkonecsni.soccerbet.football.data.api.FootballDataService;

public interface CommonService {

    Long getIdFromUrl(String url);

    FootballDataService getFootballDataService();

    void calculateAndSavePoints(Match match, User user);
}
