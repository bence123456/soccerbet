package com.bkonecsni.soccerbet.controllers;

import com.bkonecsni.soccerbet.domain.Team;
import com.bkonecsni.soccerbet.domain.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @RequestMapping("/create")
    @ResponseBody
    public String create(Long id, String name) {
        Team team;
        try {
            team = new Team(id, name);
            teamRepository.save(team);
        }
        catch (Exception ex) {
            return "Error creating the team: " + ex.toString();
        }
        return "Team succesfully created! " + team.toString();
    }

    @RequestMapping("/listTeams")
    @ResponseBody
    public String listTeams() {
        String teams = "";
        try {
            for (Team team : teamRepository.findAll()) {
                teams += team.toString() + ", ";
            }
        }
        catch (Exception ex) {
            return "Error creating the team: " + ex.toString();
        }
        return "List of teams: " + teams;
    }
}
