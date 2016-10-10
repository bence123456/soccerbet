package com.bkonecsni.soccerbet.football.data.api;

import com.bkonecsni.soccerbet.football.data.domain.fixtures.FixtureList;
import com.bkonecsni.soccerbet.football.data.domain.teams.TeamList;
import retrofit.Call;
import retrofit.http.GET;

public interface FootballDataService {

    @GET("v1/soccerseasons/432/teams")
    Call<TeamList> listTeams();

    @GET("v1/competitions/432/fixtures")
    Call<FixtureList> listFixtures();
}