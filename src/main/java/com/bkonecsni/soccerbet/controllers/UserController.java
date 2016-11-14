package com.bkonecsni.soccerbet.controllers;

import com.bkonecsni.soccerbet.domain.User;
import com.bkonecsni.soccerbet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/user/create")
    public String create(String id, String name) {
        try {
            User user = new User(id, name);
            if (!userRepository.exists(id)) {
                userRepository.save(user);
            }
        }
        catch (Exception ex) {
           ex.toString();
        }
        return "login";
    }
}
