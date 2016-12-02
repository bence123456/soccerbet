package com.bkonecsni.soccerbet.services.team;

import com.bkonecsni.soccerbet.domain.entities.Team;
import com.bkonecsni.soccerbet.services.common.CommonService;
import com.bkonecsni.soccerbet.services.football.data.api.FootballDataService;
import com.bkonecsni.soccerbet.football.data.domain.teams.ApiTeam;
import com.bkonecsni.soccerbet.football.data.domain.teams.TeamList;
import com.bkonecsni.soccerbet.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit.Call;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CommonService commonService;

    @PostConstruct
    private void persistTeamsIfNecessaryOnStartup() throws IOException {
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
        for (ApiTeam apiTeam : teamList.getTeams()) {
            Long id = commonService.getIdFromUrl(apiTeam.get_links().getSelf().getHref());
            Team team = new Team(id, apiTeam.getName());

            teamRepository.save(team);
        }
    }
}