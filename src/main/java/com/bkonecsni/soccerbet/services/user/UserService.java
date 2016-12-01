package com.bkonecsni.soccerbet.services.user;

import com.bkonecsni.soccerbet.domain.User;

public interface UserService {

    User createUser(String id, String name);
}