package com.bkonecsni.soccerbet.services.match.pointcalculator;

import com.bkonecsni.soccerbet.domain.Match;

public interface PointCalculatorAndPersisterService {

    void calculateAndSavePoints(Match match);
}