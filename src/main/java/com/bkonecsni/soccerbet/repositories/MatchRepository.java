package com.bkonecsni.soccerbet.repositories;

import com.bkonecsni.soccerbet.domain.entities.Match;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MatchRepository extends CrudRepository<Match, Long>{

    List<Match> findByStatus(@Param("status") String status);

    @Query("SELECT max(match.round) FROM Match match WHERE match.status='FINISHED'")
    int findMaxFinishedRound();

    List<Match> findByRound(@Param("round") int round);
}
