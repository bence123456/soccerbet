package com.bkonecsni.soccerbet.services.match;

import com.bkonecsni.soccerbet.domain.MatchResult;
import com.bkonecsni.soccerbet.services.common.CommonService;
import com.bkonecsni.soccerbet.domain.entities.Team;
import com.bkonecsni.soccerbet.domain.entities.Match;
import com.bkonecsni.soccerbet.services.football.data.api.FootballDataService;
import com.bkonecsni.soccerbet.football.data.domain.fixtures.Fixture;
import com.bkonecsni.soccerbet.football.data.domain.fixtures.FixtureList;
import com.bkonecsni.soccerbet.football.data.domain.fixtures.FixtureWrapper;
import com.bkonecsni.soccerbet.repositories.MatchRepository;
import com.bkonecsni.soccerbet.repositories.TeamRepository;
import com.bkonecsni.soccerbet.services.match.pointcalculator.PointCalculatorAndPersisterService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import retrofit.Call;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private PointCalculatorAndPersisterService pointCalculatorAndPersisterService;

    private final int oneMinInMs = 60000;

    @Scheduled(fixedRate = oneMinInMs)
    private void updateScheduledMatchStatus() throws IOException {
        List<Match> scheduledMatchList = matchRepository.findByStatus("SCHEDULED");
        for (Match match : scheduledMatchList) {
            if (new DateTime(match.getDateTime()).isBefore(new DateTime())) {
                match.setStatus("IN_PLAY");
                matchRepository.save(match);
            }
        }
    }

    @Scheduled(fixedRate = oneMinInMs)
    private void updateInPlayMatchStatus() throws IOException {
        List<Match> matchesInPlay = matchRepository.findByStatus("IN_PLAY");
        for (Match match : matchesInPlay) {
            if (elapsed105To130MinsFromStart(match)) {
                Fixture fixtureById = getFixtureById(match.getId().intValue());
                updateMatchBetAndUserIfNecessary(match, fixtureById);
            }
        }
    }

    private boolean elapsed105To130MinsFromStart(Match match) {
        DateTime matchDate = new DateTime(match.getDateTime());
        boolean elapsedMoreThan105Mins = matchDate.isBefore(new DateTime().plusMinutes(105));
        boolean elapsedLessThan130Mins = matchDate.isAfter(new DateTime().plusMinutes(130));

        return elapsedMoreThan105Mins && elapsedLessThan130Mins;
    }

    private void updateMatchBetAndUserIfNecessary(Match match, Fixture fixtureById) {
        if (fixtureById != null && fixtureById.getResult().getGoalsHomeTeam() != null) {
            updateMatchFields(match, fixtureById);
            matchRepository.save(match);
            pointCalculatorAndPersisterService.calculateAndSavePoints(match);
        }
    }

    private void updateMatchFields(Match match, Fixture fixtureById) {
        Integer homeTeamGoals = fixtureById.getResult().getGoalsHomeTeam();
        Integer awayTeamGoals = fixtureById.getResult().getGoalsAwayTeam();

        match.setStatus("FINISHED");
        match.setHomeTeamGoals(homeTeamGoals);
        match.setAwayTeamGoals(awayTeamGoals);
        match.setMatchResult(MatchResult.calculateMatchResult(homeTeamGoals, awayTeamGoals));
    }

    @Scheduled(fixedRate = oneMinInMs*60*24)
    private void persistMatchesIfNecessary() throws IOException {
        FixtureList fixtureList = getFixtureList();

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
        Team homeTeam = loadTeamFromDB(fixture.get_links().getHomeTeam().getHref());
        Team awayTeam = loadTeamFromDB(fixture.get_links().getAwayTeam().getHref());
        String homeTeamName = fixture.getHomeTeamName();
        String awayTeamName = fixture.getAwayTeamName();
        Integer homeTeamGoals = fixture.getResult().getGoalsHomeTeam();
        Integer awayTeamGoals = fixture.getResult().getGoalsAwayTeam();
        String status = fixture.getStatus();
        int round = fixture.getMatchday();
        Date date = fixture.getDate();

        return new Match(id, homeTeam, awayTeam, homeTeamName, awayTeamName, homeTeamGoals, awayTeamGoals, status, round, date);
    }

    private Team loadTeamFromDB(String teamUrl) {
        Long teamId = commonService.getIdFromUrl(teamUrl);

        return teamRepository.findOne(teamId);
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