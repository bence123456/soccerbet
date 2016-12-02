package com.bkonecsni.soccerbet.repositories;

import com.bkonecsni.soccerbet.domain.entities.Bet;
import com.bkonecsni.soccerbet.domain.entities.Match;
import com.bkonecsni.soccerbet.domain.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BetRepository extends CrudRepository<Bet, Long> {

    Bet findByUserAndMatch(User user, Match match);

    List<Bet> findByMatch(Match match);

    List<Bet> findByUserId(@Param("userId") String userId);
}
