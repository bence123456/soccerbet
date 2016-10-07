package com.bkonecsni.soccerbet.controllers;

import com.bkonecsni.soccerbet.data_api.GitHubService;
import com.bkonecsni.soccerbet.data_api.domain.TeamList;
import com.bkonecsni.soccerbet.domain.Team;
import com.bkonecsni.soccerbet.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import java.io.IOException;
import java.util.List;

@Controller
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @RequestMapping("/team/create")
    @ResponseBody
    public String create(Long id, String name) {
        Team team;
        try {
            team = new Team(id, name);
            teamRepository.save(team);
        }
        catch (Exception ex) {
            return "Error creating the team: " + ex.toString();
        }
        return "Team succesfully created! " + team.toString();
    }

    @RequestMapping("/team/listAll")
    @ResponseBody
    public String listTeams() {
        String teams = "";
        try {
            for (Team team : teamRepository.findAll()) {
                teams += team.toString() + ", ";
            }
        }
        catch (Exception ex) {
            return "Error creating the team: " + ex.toString();
        }
        return "List of teams: " + teams;
    }

    @RequestMapping("/team/getTeams")
    @ResponseBody
    public String getTeams() throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.football-data.org/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        GitHubService gitHubService = retrofit.create(GitHubService.class);
        Call<TeamList> call = gitHubService.listTeams();
        TeamList result = call.execute().body();

        return result.toString();
    }
}
