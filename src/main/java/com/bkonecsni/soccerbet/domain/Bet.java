package com.bkonecsni.soccerbet.domain;

import javax.persistence.*;

@Entity
@Table(name = "bet", catalog = "soccerbet")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Match.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "match_id")
    private Match match;

    private int homeTeamGoals;

    private int awayTeamGoals;

    public Bet() {}

    public Bet(User user, Match match, int homeTeamGoals, int awayTeamGoals) {
        this.user = user;
        this.match = match;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", user=" + user +
                ", match=" + match +
                ", homeTeamGoals=" + homeTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                '}';
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
}
