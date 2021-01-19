package com.itAcademy.ex14diceplayer.controller;

import com.itAcademy.ex14diceplayer.model.Player;
import com.itAcademy.ex14diceplayer.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    PlayerServiceImpl playerService;

    //Add a new Player
    @PostMapping
    public ResponseEntity<?> addNewPlayer(@RequestBody Player player) {
        playerService.addPlayer(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(player);
    }

    //get a player by id
    @GetMapping("/{id}")
    public Optional<Player> getPlayerById(@PathVariable Long id) {
        return playerService.findPlayerById(id);
    }

    //Update a player by id
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlayerById(@RequestBody Player playerUpdate) {
        playerService.updatePlayer(playerUpdate);
        return ResponseEntity.status(HttpStatus.CREATED).body(playerUpdate);
    }

    //Delete a player
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlayerById(@PathVariable Long id) {
        playerService.deletePlayerById(id);
        return ResponseEntity.ok().build();
    }

    //Get all the players
    @GetMapping
    public ResponseEntity<?> getAllPlayers() {
        List<Player> list = (List<Player>) playerService.findAll();
        return ResponseEntity.ok().body(list);
    }

    //Get average ranking results
    @GetMapping("/ranking")
    public Double getAvgRanking() {
        List<Player> listRank = (List<Player>) playerService.findAll();
        return playerService.showRankingWinnersAvg(listRank);
    }

    //Get the player with the highest ranking
    @GetMapping("/ranking/winner")
    public ResponseEntity<?> getWinner() {
        Player winner = playerService.findPlayerByWinnerAvg();
        return ResponseEntity.ok().body(winner);
    }

    //Get the player with the lowest ranking
    @GetMapping("/ranking/loser")
    public ResponseEntity<?> getloser() {
        Player loser = playerService.findPlayerByLowAvg();
        return ResponseEntity.ok().body(loser);
    }
}
