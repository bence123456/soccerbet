package com.bkonecsni.soccerbet.repositories;

import com.bkonecsni.soccerbet.domain.Bet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BetRepository extends CrudRepository<Bet, Long> {
}
