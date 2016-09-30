package com.bkonecsni.soccerbet.controllers;

import com.bkonecsni.soccerbet.data_api.FootballDataApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class MainController {

    FootballDataApi footballDataApi = new FootballDataApi();

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "yeah";
    }

    @RequestMapping("/ja")
    @ResponseBody
    public String teams() throws IOException {
        return "";
        //return footballDataApi.getTeams();
    }
}
