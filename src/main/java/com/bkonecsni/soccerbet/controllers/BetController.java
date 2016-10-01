package com.bkonecsni.soccerbet.controllers;

import com.bkonecsni.soccerbet.domain.Bet;
import com.bkonecsni.soccerbet.domain.Match;
import com.bkonecsni.soccerbet.domain.Team;
import com.bkonecsni.soccerbet.domain.User;
import com.bkonecsni.soccerbet.repositories.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BetController {

    @Autowired
    private BetRepository betRepository;

    @RequestMapping("/bet/create")
    @ResponseBody
    public String create(User user, Match match, int homeTeamGoals, int awayTeamGoals) {
        Bet bet;
        try {
            bet = new Bet(user, match, homeTeamGoals, awayTeamGoals);
            betRepository.save(bet);
        }
        catch (Exception ex) {
            return "Error creating the bet: " + ex.toString();
        }
        return "Bet succesfully created! " + bet.toString();
    }

    @RequestMapping("/bet/listAll")
    @ResponseBody
    public String listTeams() {
        String bets = "";
        try {
            for (Bet bet : betRepository.findAll()) {
                bets += bet.toString() + ", ";
            }
        }
        catch (Exception ex) {
            return "Error creating the bet: " + ex.toString();
        }
        return "List of bets: " + bets;
    }
}
