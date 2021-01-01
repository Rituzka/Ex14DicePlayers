package com.itAcademy.ex14diceplayer.service;


import com.itAcademy.ex14diceplayer.exception.ResourceNotFoundException;
import com.itAcademy.ex14diceplayer.model.Player;
import com.itAcademy.ex14diceplayer.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements IPlayerService {
    @Autowired
    private IPlayerRepository playerRepository;

    //Add new player
    @Override
    @Transactional
    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }
    //Find all players
    @Override
    @Transactional(readOnly = true)
    public Iterable<Player> findAll() {
        return playerRepository.findAll();
    }
    //find all players with pageable
    @Override
    @Transactional(readOnly = true)
    public Page<Player> findAll(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }
    //Find a player by id
    @Override
    @Transactional(readOnly = true)
    public Optional<Player> findPlayerById(Long id) {
        Optional<Player> playerFound = playerRepository.findById(id);
        if(playerFound.isPresent())
        return playerFound;
        else
            throw new ResourceNotFoundException("Player not found");
    }

    @Override
    @Transactional
    public void updatePlayer(Player playerRequest) {
        playerRepository.findById(playerRequest.getId()).map(player -> {

            player.setUsername(playerRequest.getUsername());
            player.setRegistrationDate(playerRequest.getRegistrationDate());
            player.setGames(playerRequest.getGames());
            player.setWinnerAvg(player.getWinnerAvg());

                    return playerRepository.save(player);
        }
        ).orElseThrow(() -> new ResourceNotFoundException("Shop not found"));
    }

    @Override
    @Transactional
    public void deletePlayerById(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public void deleteAllPlayers() {

    }

    @Override
    public Double showWinnersRanking(List<Player> players) {
        return null;
    }

    @Override
    public Player findPlayerByWinnerAvg() {
        return null;
    }

    @Override
    public Player findPlayerByLowAvg() {
        return null;
    }
}
