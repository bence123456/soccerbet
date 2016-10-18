package com.bkonecsni.soccerbet.controllers;

import com.bkonecsni.soccerbet.common.CommonService;
import com.bkonecsni.soccerbet.football.data.api.FootballDataService;
import com.bkonecsni.soccerbet.football.data.domain.teams.Team;
import com.bkonecsni.soccerbet.football.data.domain.teams.TeamList;
import com.bkonecsni.soccerbet.domain.DBTeam;
import com.bkonecsni.soccerbet.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import retrofit.Call;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Controller
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CommonService commonService;

    @RequestMapping("/team/create")
    @ResponseBody
    public String create(Long id, String name) {
        DBTeam team;
        try {
            team = new DBTeam(id, name);
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
            for (DBTeam team : teamRepository.findAll()) {
                teams += team.toString() + ", ";
            }
        }
        catch (Exception ex) {
            return "Error creating the team: " + ex.toString();
        }
        return "List of teams: " + teams;
    }

    @PostConstruct
    private void persistTeamsIfNecessary() throws IOException {
        FootballDataService footballDataService = commonService.getFootballDataService();
        Call<TeamList> call = footballDataService.listTeams();
        TeamList teamList = call.execute().body();

        if (teamList != null && teamList.getCount() != teamRepository.count()) {
            persistTeams(teamList);
        }
    }

    private void persistTeams(TeamList teamList) {
        for (Team dataApiTeam : teamList.getTeams()) {
            Long id = commonService.getIdFromUrl(dataApiTeam.get_links().getSelf().getHref());
            DBTeam dbTeam = new DBTeam(id, dataApiTeam.getName());

            teamRepository.save(dbTeam);
        }
    }
}
