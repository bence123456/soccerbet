package com.bkonecsni.soccerbet.football.data.domain.fixtures;

public class FixtureWrapper {
    private Fixture fixture;

    public FixtureWrapper(Fixture fixture) {
        this.fixture = fixture;
    }

    public Fixture getFixture() {
        return fixture;
    }

    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }
}
