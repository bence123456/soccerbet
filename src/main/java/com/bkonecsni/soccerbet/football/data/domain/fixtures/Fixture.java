package com.bkonecsni.soccerbet.football.data.domain.fixtures;

import java.util.Date;

public class Fixture {

    private _links _links;

    private Date date;

    private String status;

    private int matchday;

    private String homeTeamName;

    private String awayTeamName;

    private Result result;

    public _links get_links() {
        return _links;
    }

    public void set_links(_links _links) {
        this._links = _links;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMatchday() {
        return matchday;
    }

    public void setMatchday(int matchday) {
        this.matchday = matchday;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
