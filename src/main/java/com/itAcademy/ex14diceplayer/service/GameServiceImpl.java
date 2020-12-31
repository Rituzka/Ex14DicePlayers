package com.itAcademy.ex14diceplayer.service;

import com.itAcademy.ex14diceplayer.exception.ResourceNotFoundException;
import com.itAcademy.ex14diceplayer.model.Game;
import com.itAcademy.ex14diceplayer.model.Player;
import com.itAcademy.ex14diceplayer.repository.IGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class GameServiceImpl implements IGameService {
    @Autowired
    private IGameRepository gameRepository;
    @Autowired
    private PlayerServiceImpl playerservice;

    //Add a game
    @Override
    public Game addGame(Game game) {
        return gameRepository.save(game);
    }
    //find a game by id
    @Override
    @Transactional(readOnly = true)
    public Optional<Game> findGameById(Long id) {
        Optional<Game> gamefound = gameRepository.findById(id);
        if(gamefound.isPresent())
           return gamefound;
        else throw new ResourceNotFoundException("Game not found");
    }
    //gives a list of games of one Player
    @Override
    @Transactional(readOnly = true)
    public List<Game> findAllGamesByPlayer(Player player) {
        return gameRepository.findAllByPlayer(player);
    }
   //delete
    @Override
    @Transactional
    public void deleteById(Long id) {

    }

    @Override
    @Transactional
    public void deleteAllGames() {

    }

    @Override
    public int rollDice(Player player) {
        return 0;
    }

    @Override
    public boolean isWinner(int dice1, int dice2) {
        return false;
    }

    @Override
    @Transactional
    public void updateWinAvg(Player player) {

    }




    /*@Override
    @Transactional(readOnly = true)
    public Iterable<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Game> findAll(Pageable pageable) {
        return gameRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    @Transactional
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }*/
}

