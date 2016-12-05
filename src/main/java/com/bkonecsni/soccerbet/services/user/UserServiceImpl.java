package com.bkonecsni.soccerbet.services.user;

import com.bkonecsni.soccerbet.domain.entities.User;
import com.bkonecsni.soccerbet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUserIfNotExists(String id, String name) {
        if (!userRepository.exists(id)) {
            User newUser = new User(id, name);
            userRepository.save(newUser);
        }
    }
}