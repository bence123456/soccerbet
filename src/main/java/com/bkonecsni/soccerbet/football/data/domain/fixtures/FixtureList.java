package com.bkonecsni.soccerbet.football.data.domain.fixtures;

import java.util.List;

public class FixtureList {

    private List<Fixture> fixtures;

    private int count;

    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
