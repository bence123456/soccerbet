package com.bkonecsni.soccerbet.services.common;

import com.bkonecsni.soccerbet.services.football.data.api.FootballDataService;
import org.springframework.stereotype.Service;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

@Service
public class CommonServiceImpl implements CommonService{

    private FootballDataService footballDataService = createFootballDataService();

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

    private FootballDataService createFootballDataService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.football-data.org/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit.create(FootballDataService.class);
    }
}