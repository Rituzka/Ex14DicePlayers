package com.itAcademy.ex14diceplayer.service;

import com.itAcademy.ex14diceplayer.exception.ResourceNotFoundException;
import com.itAcademy.ex14diceplayer.model.Game;
import com.itAcademy.ex14diceplayer.model.Player;
import com.itAcademy.ex14diceplayer.repository.IGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
        if (gamefound.isPresent())
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
        gameRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllGames() {
        gameRepository.deleteAll();
    }

    @Override
    public Long rollDice(Player player) {
        int dice1 = (int) Math.floor(Math.random() * 6 + 1);
        int dice2 = (int) Math.floor(Math.random() * 6 + 1);
        int result = dice1 + dice2;

        boolean isWinner = isWinner(result);

        Game newGame = new Game(dice1, dice2, result, isWinner, player);
        this.addGame(newGame);
        player.setGames(newGame);
        winAvg(player);
        playerservice.save(player);

        return newGame.getId();
    }

    @Override
    public void winAvg(Player player) {
        List<Game> gamesWon = gameRepository.findAllByPlayer(player);
        successAverage(gamesWon);
    }

    @Override
    public boolean isWinner(int result) {
        return result == 7;
    }

    //PRIVATE METHODS
    //calculates the average of all games from one player
    public void successAverage(List<Game> successRolls) {
        double totalGames = successRolls.stream().map(Game::getResult).count();

        List<Game> success = successRolls.stream()
                .filter(s -> s.getResult() == 7)
                .collect(Collectors.toList());

        roundDecimals(success.size() / totalGames);

    }

    //support method for successAverage, round decimals in the result percentage
    private void roundDecimals(double number) {
        DecimalFormat formatter = new DecimalFormat("###.##%");
        formatter.format(number);
    }
}