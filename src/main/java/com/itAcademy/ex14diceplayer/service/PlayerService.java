package com.itAcademy.ex14diceplayer.service;


import com.itAcademy.ex14diceplayer.exception.ResourceNotFoundException;
import com.itAcademy.ex14diceplayer.model.Player;
import com.itAcademy.ex14diceplayer.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService  {

    @Autowired
    private IPlayerRepository playerRepository;


    public List<Player> getAllPlayers(){
        return  playerRepository.findAll();
    }

    public Player getPlayerById(long id) {
        Optional<Player> playerDB = playerRepository.findById(id);

        if(playerDB.isPresent())
            return playerDB.get();
        else
            throw new ResourceNotFoundException("Player not found");
    }

    public void addPlayer(Player player) {
        playerRepository.save(player);
    }

    public void updatePlayer(long playerId, Player playerRequest) {
        playerRepository.findById(playerId).map(player -> {

            player.setUsername(playerRequest.getUsername());

            return playerRepository.save(player);
        }).orElseThrow(() -> new ResourceNotFoundException("Player not found"));
    }

    public void deletePlayer(long id) {
        Optional<Player> playerDB = playerRepository.findById(id);

        if(playerDB.isPresent())
            playerRepository.delete(playerDB.get());
        else
            throw new ResourceNotFoundException("Player not found");
    }


}
