package com.bkonecsni.soccerbet.controllers;

import com.bkonecsni.soccerbet.domain.User;
import com.bkonecsni.soccerbet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/user/create")
    @ResponseBody
    public String create(String name, String password, int points) {
        User user;
        try {
            user = new User(name, password, points);
            userRepository.save(user);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created! " + user.toString();
    }

    @RequestMapping("/user/listAll")
    @ResponseBody
    public String listTeams() {
        String bets = "";
        try {
            for (User bet : userRepository.findAll()) {
                bets += bet.toString() + ", ";
            }
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "List of users: " + bets;
    }
}
