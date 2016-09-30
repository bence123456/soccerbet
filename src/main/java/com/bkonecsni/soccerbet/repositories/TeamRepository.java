package com.bkonecsni.soccerbet.repositories;

import com.bkonecsni.soccerbet.domain.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TeamRepository extends CrudRepository<Team, Long> {
    Team findByName(String name);
}