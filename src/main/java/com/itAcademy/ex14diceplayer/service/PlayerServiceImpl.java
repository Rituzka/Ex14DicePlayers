package com.itAcademy.ex14diceplayer.service;


import com.itAcademy.ex14diceplayer.exception.ResourceNotFoundException;
import com.itAcademy.ex14diceplayer.model.Game;
import com.itAcademy.ex14diceplayer.model.Player;
import com.itAcademy.ex14diceplayer.model.sequencegenerator.SequenceGeneratorService;
import com.itAcademy.ex14diceplayer.repository.IGameRepository;
import com.itAcademy.ex14diceplayer.repository.IPlayerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements IPlayerService {

    @Autowired
    private IPlayerRepository playerRepository;
    @Autowired
    private SequenceGeneratorService sequenceGenerator;


    //Add a new player
    @Override
    public void addPlayer(Player player) {
        player.setId(sequenceGenerator.generateSequence(Player.SEQUENCE_NAME));
        playerRepository.save(player);
    }

    //Find all players
    @Override
    public Iterable<Player> findAll() {
        return playerRepository.findAll();
    }

    //Find a player by Id
    @Override
    public Optional<Player> findPlayerById(Long id) {
        Optional<Player> playerFound = playerRepository.findById(id);
        if (playerFound.isPresent())
            return playerFound;
        else
            throw new ResourceNotFoundException("Player not found");
    }

    //Modify username of a player
    @Override
    public void updatePlayer(Player playerRequest) {
        playerRepository.findById(playerRequest.getId()).map(playerDB -> {
                    BeanUtils.copyProperties(playerRequest, playerDB);
                    ;
                    return playerRepository.save(playerDB);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Shop not found"));
    }

    //Show players win average
    @Override
    public Double showRankingWinnersAvg(List<Player> players) {
        return players
                .stream()
                .collect(Collectors.averagingDouble(Player::getWinnerAvg));
    }

    //Find the Player with the highest win average
    @Override
    public Player findPlayerByWinnerAvg() {
        List<Player> players = (List<Player>) this.findAll();

        return players
                .stream()
                .max(Comparator.comparing(Player::getWinnerAvg))
                .orElseThrow(NoSuchElementException::new);
    }

    //Find the Player with the lowest win average
    @Override
    public Player findPlayerByLowAvg() {
        List<Player> players = (List<Player>) this.findAll();

        return players
                .stream()
                .min(Comparator.comparing(Player::getWinnerAvg))
                .orElseThrow(NoSuchElementException::new);
    }

    //Add new game to a player
    @Override
    public void addNewGame(Game game, Long player_id){
        Optional<Player> playerfound = playerRepository.findById(player_id);
        if(playerfound.isPresent()) playerfound.get().addGame(game);
        else
            throw new ResourceNotFoundException("Player not found");
    }

    //Delete a player by id
    @Override
    public void deletePlayerById(Long id) {
        playerRepository.deleteById(id);
    }



}
