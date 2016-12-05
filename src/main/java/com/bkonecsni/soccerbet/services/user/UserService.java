package com.bkonecsni.soccerbet.services.user;

public interface UserService {

    void createUserIfNotExists(String id, String name);
}