package com.itAcademy.ex14diceplayer.service;

import com.itAcademy.ex14diceplayer.model.Game;
import com.itAcademy.ex14diceplayer.model.Player;


import java.util.List;
import java.util.Optional;

public interface IGameService {

    //Add new game
    Game addGame(Game game);

    //Find a game by id
    Optional <Game> findGameById(Long id);

    //Get list of games by an specific player
    List<Game> findAllGamesByPlayer(Player player);

    //Delete a game by id
    void deleteById(Long id);

    //Delete all games
    void deleteAllGames();

    //Roll dice
    int  rollDice(Player player);

    //Player wins or not
    boolean isWinner(int dice1, int dice2);

    //update win average rolls
    void updateWinAvg(Player player);

}
