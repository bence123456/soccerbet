package com.bkonecsni.soccerbet.controllers;

import com.bkonecsni.soccerbet.domain.entities.User;
import com.bkonecsni.soccerbet.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/create")
    @ResponseBody
    public User save(String id, String name) {
        return userService.createUserIfNotExists(id, name);
    }
}