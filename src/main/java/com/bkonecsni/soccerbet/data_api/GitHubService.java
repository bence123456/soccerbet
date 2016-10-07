package com.bkonecsni.soccerbet.data_api;

import com.bkonecsni.soccerbet.data_api.domain.TeamList;
import retrofit.Call;
import retrofit.http.GET;

//TODO: rename and move /v1 to controller
public interface GitHubService {

    @GET("/v1/soccerseasons/432/teams")
    Call<TeamList> listTeams();
}
