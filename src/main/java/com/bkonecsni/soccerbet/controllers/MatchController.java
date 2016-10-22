package com.bkonecsni.soccerbet.controllers;

import com.bkonecsni.soccerbet.common.CommonService;
import com.bkonecsni.soccerbet.domain.Match;
import com.bkonecsni.soccerbet.domain.DBTeam;
import com.bkonecsni.soccerbet.football.data.api.FootballDataService;
import com.bkonecsni.soccerbet.football.data.domain.fixtures.Fixture;
import com.bkonecsni.soccerbet.football.data.domain.fixtures.FixtureList;
import com.bkonecsni.soccerbet.repositories.MatchRepository;
import com.bkonecsni.soccerbet.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import retrofit.Call;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Date;

@Controller
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CommonService commonService;

    @RequestMapping("/match/create")
    @ResponseBody
    public String create(Long id, DBTeam homeTeam, DBTeam awayTeam, String homeTeamName, String awayTeamName,
                         int homeTeamGoals, int awayTeamGoals, String status, int round, Date dateTime) {
        Match match;
        try {
            match = new Match(id, homeTeam, awayTeam, homeTeamName, awayTeamName, homeTeamGoals, awayTeamGoals, status, round, dateTime);
            matchRepository.save(match);
        }
        catch (Exception ex) {
            return "Error creating the match: " + ex.toString();
        }
        return "Match succesfully created! " + match.toString();
    }

    @RequestMapping("/match/listAll")
    @ResponseBody
    public String listTeams() {
        String bets = "";
        try {
            for (Match match : matchRepository.findAll()) {
                bets += match.toString() + ", ";
            }
        }
        catch (Exception ex) {
            return "Error creating the match: " + ex.toString();
        }
        return "List of matches: " + bets;
    }

    //@PostConstruct
    private void persistMatchesIfNecessary() throws IOException {
        FootballDataService footballDataService = commonService.getFootballDataService();
        Call<FixtureList> call = footballDataService.listFixtures();
        FixtureList fixtureList = call.execute().body();

        if (fixtureList != null) {
            for (Fixture fixture : fixtureList.getFixtures()) {
                Long id = commonService.getIdFromUrl(fixture.get_links().getSelf().getHref());
                if (!matchRepository.exists(id)) {
                    persistMatch(fixture, id);
                }
            }
        }
    }

    private void persistMatch(Fixture fixture, Long id) {
        Match match = createMatchFromFixture(fixture, id);

        matchRepository.save(match);
    }

    private Match createMatchFromFixture(Fixture fixture, Long id) {
        DBTeam homeTeam = loadTeamFromDB(fixture.get_links().getHomeTeam().getHref());
        DBTeam awayTeam = loadTeamFromDB(fixture.get_links().getAwayTeam().getHref());
        String homeTeamName = fixture.getHomeTeamName();
        String awayTeamName = fixture.getAwayTeamName();
        int homeTeamGoals = fixture.getResult().getGoalsHomeTeam();
        int awayTeamGoals = fixture.getResult().getGoalsAwayTeam();
        String status = fixture.getStatus();
        int round = fixture.getMatchday();
        Date date = fixture.getDate();


        return new Match(id, homeTeam, awayTeam, homeTeamName, awayTeamName, homeTeamGoals, awayTeamGoals, status, round, date);
    }

    //TODO: move it to commonservice?
    private DBTeam loadTeamFromDB(String teamUrl) {
        Long teamId = commonService.getIdFromUrl(teamUrl);

        return teamRepository.findOne(teamId);
    }
}
