package com.bkonecsni.soccerbet.football.data.api;

import com.bkonecsni.soccerbet.football.data.domain.TeamList;
import retrofit.Call;
import retrofit.http.GET;

public interface FootballDataService {

    @GET("/soccerseasons/432/teams")
    Call<TeamList> listTeams();
}