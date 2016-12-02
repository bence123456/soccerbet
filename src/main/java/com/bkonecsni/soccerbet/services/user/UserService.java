package com.bkonecsni.soccerbet.services.user;

import com.bkonecsni.soccerbet.domain.entities.User;

public interface UserService {

    User createUser(String id, String name);
}