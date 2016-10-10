package com.bkonecsni.soccerbet.repositories;

import com.bkonecsni.soccerbet.domain.DBTeam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TeamRepository extends CrudRepository<DBTeam, Long> {
    DBTeam findByName(String name);
}