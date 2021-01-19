package com.itAcademy.ex14diceplayer.controller;

import com.itAcademy.ex14diceplayer.model.Game;
import com.itAcademy.ex14diceplayer.model.Player;
import com.itAcademy.ex14diceplayer.service.GameServiceImpl;
import com.itAcademy.ex14diceplayer.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/players")
public class GameController {

    @Autowired
    GameServiceImpl gameService;
    @Autowired
    PlayerServiceImpl playerService;

    //Create new game by a player
    @PostMapping("/{id}/games")
    public ResponseEntity<?> addNewGame(@PathVariable(name = "id")Long id) {
        List<Game> games = new ArrayList<>();
          Optional <Player> playerFound = playerService.findPlayerById(id);
          if(playerFound.isPresent()) {
              Long gameId = gameService.rollDice(playerFound.get());
              Game newGame = gameService.findGameById(gameId);
              games.add(newGame);
          }
         return ResponseEntity.ok().body(games);
    }

    //get a game by id
    @GetMapping("/games/{id}")
    public ResponseEntity<?> getGameById(@PathVariable Long id) {
        Game game = gameService.findGameById(id);
        return ResponseEntity.ok().body(game);
    }

    //Delete a game
    @DeleteMapping("/games/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable Long id) {
        gameService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    //Get games from a player
    @GetMapping("/{id}/games")
    public ResponseEntity<?> getGamesByPlayer(@PathVariable(name = "id") Player player) {
        List<Game> games = gameService.findAllGamesByPlayer(player.getId());
        return ResponseEntity.ok().body(games);
    }
}
