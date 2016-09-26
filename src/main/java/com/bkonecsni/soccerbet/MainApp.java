package com.bkonecsni.soccerbet;

import com.bkonecsni.soccerbet.common.Dao;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;

@Controller
@EnableAutoConfiguration
public class MainApp {

    Dao dao;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/asd")
    @ResponseBody
    String asd() {

        return "";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainApp.class, args);
    }
}
