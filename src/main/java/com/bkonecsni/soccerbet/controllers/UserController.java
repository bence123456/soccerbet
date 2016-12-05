package com.bkonecsni.soccerbet.controllers;

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
    public void create(String id, String name) {
        userService.createUserIfNotExists(id, name);
    }
}