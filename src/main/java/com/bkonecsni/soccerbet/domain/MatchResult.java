package com.bkonecsni.soccerbet.domain;

public enum MatchResult {

    HOME_TEAM_WINS,
    AWAY_TEAM_WINS,
    DRAW;

    public static MatchResult calculateMatchResult(int homeTeamGoals, int awayTeamGoals) {
        MatchResult matchResult = MatchResult.HOME_TEAM_WINS;

        if (homeTeamGoals == awayTeamGoals) {
            matchResult = MatchResult.DRAW;
        } else if (homeTeamGoals < awayTeamGoals) {
            matchResult = MatchResult.AWAY_TEAM_WINS;
        }

        return matchResult;
    }
}