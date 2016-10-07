package com.bkonecsni.soccerbet.data_api.domain;

import java.util.List;

public class TeamList {

    private List<Team> teams;

    private int count;

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
