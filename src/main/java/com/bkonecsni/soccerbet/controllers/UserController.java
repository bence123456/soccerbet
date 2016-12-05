package com.bkonecsni.soccerbet.controllers;

import com.bkonecsni.soccerbet.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/create")
    public void create(String id, String name) {
        userService.createUserIfNotExists(id, name);
    }
}