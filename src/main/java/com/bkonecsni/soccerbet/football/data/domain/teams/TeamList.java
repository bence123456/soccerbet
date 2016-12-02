package com.bkonecsni.soccerbet.football.data.domain.teams;

import java.util.List;

public class TeamList {

    private List<ApiTeam> teams;

    private int count;

    public List<ApiTeam> getTeams() {
        return teams;
    }

    public void setTeams(List<ApiTeam> teams) {
        this.teams = teams;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
