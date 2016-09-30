package com.bkonecsni.soccerbet.controllers;

import com.bkonecsni.soccerbet.domain.Match;
import com.bkonecsni.soccerbet.domain.Team;
import com.bkonecsni.soccerbet.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    @RequestMapping("/match/create")
    @ResponseBody
    public String create(Team homeTeam, Team awayTeam, int homeTeamGoals, int awayTeamGoals, String status, int round, Date date) {
        Match match;
        try {
            match = new Match(homeTeam, awayTeam, homeTeamGoals, awayTeamGoals, status, round, date);
            matchRepository.save(match);
        }
        catch (Exception ex) {
            return "Error creating the team: " + ex.toString();
        }
        return "Team succesfully created! " + match.toString();
    }

    @RequestMapping("/match/listAll")
    @ResponseBody
    public String listTeams() {
        String bets = "";
        try {
            for (Match match : matchRepository.findAll()) {
                bets += match.toString() + ", ";
            }
        }
        catch (Exception ex) {
            return "Error creating the team: " + ex.toString();
        }
        return "List of bets: " + bets;
    }
}
