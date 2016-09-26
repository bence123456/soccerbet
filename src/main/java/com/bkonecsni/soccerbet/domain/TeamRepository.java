package com.bkonecsni.soccerbet.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TeamRepository extends CrudRepository<Team, Long> {
    Team findByName(String name);
}