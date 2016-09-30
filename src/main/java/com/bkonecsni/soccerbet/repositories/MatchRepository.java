package com.bkonecsni.soccerbet.repositories;

import com.bkonecsni.soccerbet.domain.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MatchRepository extends CrudRepository<Match, Long>{
}
