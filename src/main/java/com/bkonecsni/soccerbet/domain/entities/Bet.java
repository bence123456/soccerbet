package com.bkonecsni.soccerbet.domain.entities;

import com.bkonecsni.soccerbet.domain.MatchResult;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "bet", catalog = "soccerbet")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Match.class)
    @JoinColumn(name = "match_id")
    private Match match;

    private int homeTeamGoals;

    private int awayTeamGoals;

    @Enumerated(STRING)
    private MatchResult matchResult;

    private Integer gainedPoints;

    public Bet() {}

    public Bet(User user, Match match, int homeTeamGoals, int awayTeamGoals) {
        this.user = user;
        this.match = match;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.matchResult = MatchResult.calculateMatchResult(homeTeamGoals, awayTeamGoals);
        this.gainedPoints = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
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

    public MatchResult getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(MatchResult matchResult) {
        this.matchResult = matchResult;
    }

    public Integer getGainedPoints() {
        return gainedPoints;
    }

    public void setGainedPoints(Integer gainedPoints) {
        this.gainedPoints = gainedPoints;
    }
}
