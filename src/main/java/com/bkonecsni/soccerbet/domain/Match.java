package com.bkonecsni.soccerbet.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "match", catalog = "soccerbet")
public class Match {

    @Id
    private Long id;

    @ManyToOne(targetEntity = DBTeam.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "home_team_id")
    private DBTeam homeTeam;

    @ManyToOne(targetEntity = DBTeam.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "away_team_id")
    private DBTeam awayTeam;

    private int homeTeamGoals;

    private int awayTeamGoals;

    private String status;

    // match day in the API
    private int round;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;


    public Match() {}

    public Match(Long id, DBTeam homeTeam, DBTeam awayTeam, int homeTeamGoals, int awayTeamGoals, String status,
                 int round, Date dateTime) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.status = status;
        this.round = round;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                ", homeTeamGoals=" + homeTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                ", status='" + status + '\'' +
                ", round=" + round +
                ", dateTime=" + dateTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DBTeam getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(DBTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    public DBTeam getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(DBTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(int homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
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
}
