package com.itAcademy.ex14diceplayer.service;

import com.itAcademy.ex14diceplayer.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPlayerService {

    //add a new player
    Player addPlayer(Player player);

    //find all players
    Iterable<Player> findAll();

    //find all player pageable
    Page<Player> findAll(Pageable pageable);

    //find a player by id
    Optional<Player> findPlayerById(Long id);

    //update a player
    void updatePlayer(Player player);

    //delete a player by id
    void deletePlayerById(Long id);

    //delete all players
    void deleteAllPlayers();

    //Ranking of player by the win average games
    Double showWinnersRanking(List<Player> players);

    //Find player with the highest win average
    Player findPlayerByWinnerAvg();

    //Find player with the lowest win average
    Player findPlayerByLowAvg();


}
