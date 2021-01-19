package com.itAcademy.ex14diceplayer.service;

import com.itAcademy.ex14diceplayer.model.Game;
import com.itAcademy.ex14diceplayer.model.Player;


import java.util.List;

public interface IGameService {

    //Add new game
    void addGame(Game game);

    //Find a game by id
    Game findGameById(Long id);

    //Get list of games by an specific player
    List<Game> findAllGamesByPlayer(Long player_id);

    //Delete a game by id
    void deleteById(Long id);

    //Delete all games
    void deleteAllGames();

    //Roll dice
    Long rollDice(Player player);

    //Player wins or not
    boolean isWinner(int rollResult);

    //update win average rolls
    void winAvg(Player player);

}
