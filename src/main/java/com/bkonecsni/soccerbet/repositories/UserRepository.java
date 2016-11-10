package com.bkonecsni.soccerbet.repositories;

import com.bkonecsni.soccerbet.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, String>{
}
