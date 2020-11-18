package com.itAcademy.ex14diceplayer.repository;

import com.itAcademy.ex14diceplayer.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IGameRepository extends JpaRepository<Game, Long> {

    List<Game> findAllByPlayerId(long playerId);
    Optional<Game> findGameByIdAndPlayerById(long gameId, long playerId);


}
