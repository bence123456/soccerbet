package com.bkonecsni.soccerbet.controllers;

import com.bkonecsni.soccerbet.common.CommonService;
import com.bkonecsni.soccerbet.football.data.api.FootballDataService;
import com.bkonecsni.soccerbet.football.data.domain.teams.Team;
import com.bkonecsni.soccerbet.football.data.domain.teams.TeamList;
import com.bkonecsni.soccerbet.domain.DBTeam;
import com.bkonecsni.soccerbet.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import retrofit.Call;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Controller
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CommonService commonService;

    @PostConstruct
    private void persistTeamsIfNecessary() throws IOException {
        if (teamRepository.count() != 64) {
            FootballDataService footballDataService = commonService.getFootballDataService();
            Call<TeamList> call = footballDataService.listTeams();
            TeamList teamList = call.execute().body();

            if (teamList != null) {
                persistTeams(teamList);
            }
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
