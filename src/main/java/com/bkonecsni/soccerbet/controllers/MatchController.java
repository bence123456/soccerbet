package com.bkonecsni.soccerbet.controllers;

import com.bkonecsni.soccerbet.common.service.CommonService;
import com.bkonecsni.soccerbet.domain.Match;
import com.bkonecsni.soccerbet.domain.DBTeam;
import com.bkonecsni.soccerbet.football.data.api.FootballDataService;
import com.bkonecsni.soccerbet.football.data.domain.fixtures.Fixture;
import com.bkonecsni.soccerbet.football.data.domain.fixtures.FixtureList;
import com.bkonecsni.soccerbet.football.data.domain.fixtures.FixtureWrapper;
import com.bkonecsni.soccerbet.repositories.MatchRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import retrofit.Call;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private CommonService commonService;

    @Scheduled(fixedRate = 10000)
    private void updateScheduledMatchesStatus() throws IOException {
        List<Match> scheduledMatchList = matchRepository.findByStatus("SCHEDULED");
        for (Match match : scheduledMatchList) {
            if (new DateTime(match.getDateTime()).isBefore(new DateTime())) {
                match.setStatus("IN_PLAY");
                matchRepository.save(match);
            }
        }
    }

    @Scheduled(fixedRate = 30000)
    private void updateMatchesStatus() throws IOException {
        List<Match> matchesInPlay = matchRepository.findByStatus("IN_PLAY");
        for (Match match : matchesInPlay) {
            if (elapsed110To130MinsFromStart(match)) {
                Fixture fixtureById = getFixtureById(match.getId().intValue());
                updateMatchBetAndUserIfNecessary(match, fixtureById);
            }
        }
    }

    private void updateMatchBetAndUserIfNecessary(Match match, Fixture fixtureById) {
        if (fixtureById != null && fixtureById.getResult().getGoalsHomeTeam() != null) {
            match.setStatus("FINISHED");
            match.setHomeTeamGoals(fixtureById.getResult().getGoalsHomeTeam());
            match.setAwayTeamGoals(fixtureById.getResult().getGoalsAwayTeam());
            matchRepository.save(match);
            commonService.calculateAndSavePoints(match);
        }
    }

    private boolean elapsed110To130MinsFromStart(Match match) {
        DateTime matchDate = new DateTime(match.getDateTime());
        boolean elapsedMoreThan110Mins = matchDate.isBefore(new DateTime().plusMinutes(110));
        boolean elapsedLessThan130Mins = matchDate.isAfter(new DateTime().plusMinutes(130));

        return elapsedMoreThan110Mins && elapsedLessThan130Mins;
    }

    @PostConstruct
    private void persistMatchesIfNecessaryOnStart() throws IOException {
        FixtureList fixtureList = getFixtureList();

        if (fixtureList != null) {
            for (Fixture fixture : fixtureList.getFixtures()) {
                Long id = commonService.getIdFromUrl(fixture.get_links().getSelf().getHref());
                if (!matchRepository.exists(id) || !fixture.getStatus().equals(matchRepository.findOne(id))) {
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
        Integer homeTeamGoals = fixture.getResult().getGoalsHomeTeam();
        Integer awayTeamGoals = fixture.getResult().getGoalsAwayTeam();
        String status = fixture.getStatus();
        int round = fixture.getMatchday();
        Date date = fixture.getDate();

        return new Match(id, homeTeam, awayTeam, homeTeamName, awayTeamName, homeTeamGoals, awayTeamGoals, status, round, date);
    }

    private DBTeam loadTeamFromDB(String teamUrl) {
        Long teamId = commonService.getIdFromUrl(teamUrl);

        return commonService.findTeamById(teamId);
    }

    private FixtureList getFixtureList() throws IOException {
        FootballDataService footballDataService = commonService.getFootballDataService();
        Call<FixtureList> call = footballDataService.listFixtures();
        return call.execute().body();
    }

    private Fixture getFixtureById(Integer id) throws IOException {
        FootballDataService footballDataService = commonService.getFootballDataService();
        Call<FixtureWrapper> call = footballDataService.getFixtureById(id);
        return call.execute().body().getFixture();
    }
}
