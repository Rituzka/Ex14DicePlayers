package com.itAcademy.ex14diceplayer.controller;

import com.itAcademy.ex14diceplayer.exception.ResourceNotFoundException;
import com.itAcademy.ex14diceplayer.model.Player;
import com.itAcademy.ex14diceplayer.service.IPlayerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    IPlayerService playerservice;

    //create a new Player
    @PostMapping
    public ResponseEntity<?> addNewPlayer(Player player) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerservice.save(player));
    }

    //get a player by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayerById(@PathVariable Long id) {
        Optional<Player> playerDB = playerservice.findById(id);
        if (!playerDB.isPresent())
            throw new ResourceNotFoundException("Player not found");
        else
            return ResponseEntity.ok(playerDB);
    }

    //Update a player by id
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlayerById(@PathVariable Long id, @RequestBody Player playerUpdate) {
        Optional<Player> playerDB = playerservice.findById(id);
        if (!playerDB.isPresent())
            throw new ResourceNotFoundException("Player not found");
        else {
            BeanUtils.copyProperties(playerUpdate, playerDB);
            return ResponseEntity.status(HttpStatus.CREATED).body(playerDB.get());
        }
    }

    //Delete a player
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlayerById(@PathVariable Long id) {
        if (!playerservice.findById(id).isPresent())
            throw new ResourceNotFoundException("Player not found");
        else
            playerservice.deleteById(id);
        return ResponseEntity.ok().build();

    }

    //Get all the players
    @GetMapping
    public List<Player> getAllPlayers() {
        return StreamSupport
                .stream(playerservice.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
