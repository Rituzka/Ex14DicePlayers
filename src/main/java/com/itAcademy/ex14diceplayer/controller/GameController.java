package com.itAcademy.ex14diceplayer.controller;

import com.itAcademy.ex14diceplayer.exception.ResourceNotFoundException;
import com.itAcademy.ex14diceplayer.model.Game;
import com.itAcademy.ex14diceplayer.model.Player;
import com.itAcademy.ex14diceplayer.service.GameServiceImpl;
import com.itAcademy.ex14diceplayer.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> addNewGame(@PathVariable(name = "id") Long player_id) {

          Optional <Player> playerFound = playerService.findPlayerById(player_id);
          if(playerFound.isPresent()) {
              Long gameId = gameService.rollDice(playerFound.get());
              Game newGame = gameService.findGameById(gameId);
              gameService.addGame(newGame, player_id);
              return ResponseEntity.ok().body(newGame);
          }else
              throw new ResourceNotFoundException("Player not found");
    }

    //get a game by id
    @GetMapping("/games/{id}")
    public ResponseEntity<?> getGameById(@PathVariable Long game_id) {
        Game game = gameService.findGameById(game_id);
        return ResponseEntity.ok().body(game);
    }

    //Delete a game
    @DeleteMapping("/games/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable Long game_id) {
        gameService.deleteById(game_id);
        return ResponseEntity.ok().build();
    }

    //Get games from a player
    @GetMapping("/{id}/games")
    public ResponseEntity<?> getGamesByPlayer(@PathVariable(name = "id") Long player_id) {
        Optional<Player> playerFound = playerService.findPlayerById(player_id);
        if(playerFound.isPresent()) {
            List<Game> games = gameService.findAllGamesByPlayer(player_id);
            return ResponseEntity.ok().body(games);
        }else
            throw new ResourceNotFoundException("Player not found");
    }
}
