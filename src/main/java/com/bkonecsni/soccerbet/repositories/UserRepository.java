package com.bkonecsni.soccerbet.repositories;

import com.bkonecsni.soccerbet.domain.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRepository extends CrudRepository<User, String>{

    List<User> findAllByOrderByPointsDesc();
}