package com.itAcademy.ex14diceplayer.controller;

import com.itAcademy.ex14diceplayer.exception.ResourceNotFoundException;
import com.itAcademy.ex14diceplayer.model.Game;
import com.itAcademy.ex14diceplayer.service.IGameService;
import com.itAcademy.ex14diceplayer.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/players")
public class GameController {

    @Autowired
    IGameService gameService;
    @Autowired
    IPlayerService playerService;

    //Create new game
    @PostMapping("/games")
    public ResponseEntity<?> addNewGame(@RequestBody Game game) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.addGame(game));
    }

    //get a game by id
    @GetMapping("/games/{id}")
    public ResponseEntity<?> getGameById(@PathVariable Long id) {
        Optional<Game> gameDB = gameService.findGameById(id);
        if (!gameDB.isPresent()) {
            throw new ResourceNotFoundException("Game not found");
        } else
            return ResponseEntity.ok(gameDB);
    }

    //Delete a game
    @DeleteMapping("/games/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable Long id) {
        if (!gameService.findGameById(id).isPresent()) {
            throw new ResourceNotFoundException("Game not found");
        } else {
            gameService.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
}
