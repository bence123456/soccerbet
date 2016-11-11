package com.bkonecsni.soccerbet.domain;

import com.bkonecsni.soccerbet.common.MatchResult;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.EnumType.STRING;

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

    public Match(Long id, DBTeam homeTeam, DBTeam awayTeam, String homeTeamName, String awayTeamName,
                 Integer homeTeamGoals, Integer awayTeamGoals, String status, int round, Date dateTime) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.status = status;
        this.round = round;
        this.dateTime = dateTime;
        this.matchResult = calculateMatchResult(homeTeamGoals, awayTeamGoals);
    }

    private MatchResult calculateMatchResult(Integer homeTeamGoals, Integer awayTeamGoals) {
        MatchResult matchResult = MatchResult.NOT_FINISHED;

        if (status.equals("FINISHED")) {
            matchResult = MatchResult.HOME_TEAM_WINS;

            if (homeTeamGoals == awayTeamGoals) {
                matchResult = MatchResult.DRAW;
            } else if (homeTeamGoals < awayTeamGoals) {
                matchResult = MatchResult.AWAY_TEAM_WINS;
            }
        }

        return matchResult;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                ", homeTeamName=" + homeTeamName +
                ", awayTeamName=" + awayTeamName +
                ", homeTeamGoals=" + homeTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                ", status='" + status + '\'' +
                ", round=" + round +
                ", dateTime=" + dateTime +
                ", matchResult=" + matchResult +
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
