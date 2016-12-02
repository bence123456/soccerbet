package com.bkonecsni.soccerbet.services.user;

import com.bkonecsni.soccerbet.domain.entities.User;

public interface UserService {

    User createUserIfNotExists(String id, String name);
}