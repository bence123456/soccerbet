package com.bkonecsni.soccerbet.common;

import com.bkonecsni.soccerbet.football.data.api.FootballDataService;

public interface CommonService {

    Long getIdFromUrl(String url);

    FootballDataService getFootballDataService();
}
