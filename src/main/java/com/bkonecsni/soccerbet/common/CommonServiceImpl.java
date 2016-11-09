package com.bkonecsni.soccerbet.common;

import com.bkonecsni.soccerbet.domain.Bet;
import com.bkonecsni.soccerbet.domain.DBTeam;
import com.bkonecsni.soccerbet.domain.Match;
import com.bkonecsni.soccerbet.domain.User;
import com.bkonecsni.soccerbet.football.data.api.FootballDataService;
import com.bkonecsni.soccerbet.repositories.BetRepository;
import com.bkonecsni.soccerbet.repositories.TeamRepository;
import com.bkonecsni.soccerbet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

@Service
public class CommonServiceImpl implements CommonService{

    private FootballDataService footballDataService = createFootballDataService();

    @Autowired
    BetRepository betRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public String getStringIdFromUrl(String url) {
        int lastIndexOfBackSlash = url.lastIndexOf("/");
        return url.substring(lastIndexOfBackSlash + 1);
    }

    @Override
    public Long getIdFromUrl(String url) {
        return Long.valueOf(getStringIdFromUrl(url));
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
    public void calculateAndSavePoints(Match match, User user) {
        Bet bet = betRepository.findByUserAndMatch(user, match);

        int gainedPoints = 0;
        if (match.getHomeTeamGoals() == bet.getHomeTeamGoals() && match.getAwayTeamGoals() == bet.getAwayTeamGoals()) {
            gainedPoints = 3;
        } else if (match.getMatchResult().equals(bet.getMatchResult())){
            gainedPoints = 1;
        }

        if (gainedPoints > 0) {
            user.setPoints(user.getPoints() + gainedPoints);
            userRepository.save(user);
        }
    }

    private FootballDataService createFootballDataService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.football-data.org/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit.create(FootballDataService.class);
    }
}
