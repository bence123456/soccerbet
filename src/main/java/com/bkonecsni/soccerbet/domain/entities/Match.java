package com.bkonecsni.soccerbet.domain.entities;

import com.bkonecsni.soccerbet.domain.MatchResult;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "match", catalog = "soccerbet")
public class Match {

    @Id
    private Long Id;

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    private String homeTeamName;

    private String awayTeamName;

    private Integer homeTeamGoals;

    private Integer awayTeamGoals;

    private String status;

    // match day in the API
    private int round;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @Enumerated(STRING)
    private MatchResult matchResult;

    public Match() {}

    public Match(Long id, Team homeTeam, Team awayTeam, String homeTeamName, String awayTeamName,
                 Integer homeTeamGoals, Integer awayTeamGoals, String status, int round, Date dateTime) {
        this.Id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.status = status;
        this.round = round;
        this.dateTime = dateTime;
        this.matchResult = status.equals("FINISHED") ? MatchResult.calculateMatchResult(homeTeamGoals, awayTeamGoals) : null;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
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

    public Integer getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(Integer homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public Integer getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(Integer awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public MatchResult getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(MatchResult matchResult) {
        this.matchResult = matchResult;
    }
}
