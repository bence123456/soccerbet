package com.bkonecsni.soccerbet.repositories;

import com.bkonecsni.soccerbet.domain.entities.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TeamRepository extends CrudRepository<Team, Long> {
}