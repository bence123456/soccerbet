package com.bkonecsni.soccerbet.services.common;

import com.bkonecsni.soccerbet.services.football.data.api.FootballDataService;

public interface CommonService {

    Long getIdFromUrl(String url);

    FootballDataService getFootballDataService();
}