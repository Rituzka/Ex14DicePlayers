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
    @PostMapping("/{id}/games/newgame")
    public Game addNewGame(@PathVariable(name = "id") long id) {
        Optional<Player> playerFound = playerService.findPlayerById(id);
        if (playerFound.isPresent()) {
            long gameId = gameService.rollDice(playerFound.get());
            return gameService.findGameById(gameId);
        } else
            throw new ResourceNotFoundException("Player not Found");
    }

    //get a game by id
    @GetMapping("/games/{id}")
    public ResponseEntity<?> getGameById(@PathVariable long id) {
        Game game = gameService.findGameById(id);
        return ResponseEntity.ok().body(game);
    }

    //Delete a game
    @DeleteMapping("/games/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable long id) {
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
