package com.itAcademy.ex14diceplayer.service;

import com.itAcademy.ex14diceplayer.model.Player;

import java.util.List;
import java.util.Optional;

public interface IPlayerService {

    //add a new player
    Player addPlayer(Player player);

    //find all players
    Iterable<Player> findAll();

    //find a player by id
    Optional<Player> findPlayerById(Long id);

    //update the username of a player
    void updateUsernamePlayer(Player player);

    //Ranking of player by the win average games
    Double showRankingWinnersAvg(List<Player> players);

    //Find player with the highest win average
    Player findPlayerByWinnerAvg();

    //Find player with the lowest win average
    Player findPlayerByLowAvg();

    //delete a player by id
    void deletePlayerById(Long id);
}
