package com.bkonecsni.soccerbet.services.user;

import com.bkonecsni.soccerbet.domain.User;
import com.bkonecsni.soccerbet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(String id, String name) {
        User user = userRepository.findOne(id);

        if (user == null) {
            User newUser = new User(id, name);
            userRepository.save(newUser);
            return newUser;
        }

        return user;
    }
}