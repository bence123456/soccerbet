package com.bkonecsni.soccerbet.common.service;

import com.bkonecsni.soccerbet.domain.Bet;
import com.bkonecsni.soccerbet.domain.DBTeam;
import com.bkonecsni.soccerbet.domain.Match;
import com.bkonecsni.soccerbet.domain.User;
import com.bkonecsni.soccerbet.football.data.api.FootballDataService;
import com.bkonecsni.soccerbet.repositories.BetRepository;
import com.bkonecsni.soccerbet.repositories.MatchRepository;
import com.bkonecsni.soccerbet.repositories.TeamRepository;
import com.bkonecsni.soccerbet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService{

    private FootballDataService footballDataService = createFootballDataService();

    @Autowired
    BetRepository betRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MatchRepository matchRepository;

    @Override
    public Long getIdFromUrl(String url) {
        int lastIndexOfBackSlash = url.lastIndexOf("/");
        String stringId = url.substring(lastIndexOfBackSlash + 1);

        return Long.valueOf(stringId);
    }

    @Override
    public FootballDataService getFootballDataService() {
        return footballDataService;
    }

    @Override
    public DBTeam findTeamById(Long id) {
        return teamRepository.findOne(id);
    }

    @Override
    public User findUserById(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public Match findMatchById(Long id) {
        return matchRepository.findOne(id);
    }

    @Override
    public void calculateAndSavePoints(Match match) {
        List<Bet> betList = betRepository.findByMatch(match);

        for (Bet bet : betList) {
            int gainedPoints = calculateGainedPoints(match, bet);
            bet.setGainedPoints(gainedPoints);
            betRepository.save(bet);

            updateUserPointsIfNecessary(bet, gainedPoints);
        }
    }

    private void updateUserPointsIfNecessary(Bet bet, int gainedPoints) {
        if (gainedPoints > 0) {
            User user = bet.getUser();
            user.setPoints(user.getPoints() + gainedPoints);
            userRepository.save(user);
        }
    }

    private int calculateGainedPoints(Match match, Bet bet) {
        int gainedPoints = 0;
        if (match.getHomeTeamGoals() == bet.getHomeTeamGoals() && match.getAwayTeamGoals() == bet.getAwayTeamGoals()) {
            gainedPoints = 3;
        } else if (match.getMatchResult().equals(bet.getMatchResult())){
            gainedPoints = 1;
        }
        return gainedPoints;
    }

    private FootballDataService createFootballDataService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.football-data.org/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit.create(FootballDataService.class);
    }
}
