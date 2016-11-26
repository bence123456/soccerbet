package com.bkonecsni.soccerbet;

import com.bkonecsni.soccerbet.domain.Bet;

public class BetWrapper {

    Bet bet;

    Integer expectedGainedPoints;

    public BetWrapper(Bet bet, Integer expectedGainedPoints) {
        this.bet = bet;
        this.expectedGainedPoints = expectedGainedPoints;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public Integer getExpectedGainedPoints() {
        return expectedGainedPoints;
    }

    public void setExpectedGainedPoints(Integer expectedGainedPoints) {
        this.expectedGainedPoints = expectedGainedPoints;
    }
}
