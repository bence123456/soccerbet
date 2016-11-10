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
    public String create(String id, String name) {
        User user;
        try {
            user = new User(id, name);
            if (!userRepository.exists(id)) {
                userRepository.save(user);
            }
        }
        catch (Exception ex) {
           ex.toString();
        }
        return "login";
    }

    @RequestMapping("/user/listAll")
    @ResponseBody
    public String listUsers() {
        String users = "";
        try {
            for (User user : userRepository.findAll()) {
                users += user.toString() + ", ";
            }
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "List of users: " + users;
    }
}
