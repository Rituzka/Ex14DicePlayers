package com.itAcademy.ex14diceplayer.service;

import com.itAcademy.ex14diceplayer.exception.ResourceNotFoundException;
import com.itAcademy.ex14diceplayer.model.Game;
import com.itAcademy.ex14diceplayer.model.Player;
import com.itAcademy.ex14diceplayer.model.sequencegenerator.SequenceGeneratorService;
import com.itAcademy.ex14diceplayer.repository.IGameRepository;
import com.itAcademy.ex14diceplayer.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class GameServiceImpl implements IGameService {

    @Autowired
    private IGameRepository gameRepository;
    @Autowired
    private PlayerServiceImpl playerservice;
    @Autowired
    private SequenceGeneratorService sequenceServ;
    @Autowired
    private IPlayerRepository playerRepository;


    //Add a game
    @Override
    public void addGame(Game game, Long player_id) {
        Optional<Player> playerFound = playerRepository.findById(player_id);
        if(playerFound.isPresent()) {
            game.setId(sequenceServ.generateSequence(Player.SEQUENCE_NAME));
            gameRepository.save(game);
        }else
            throw new ResourceNotFoundException("Player not found");
    }

    //find a game by id
    @Override
    public Game findGameById(Long id) {
        return gameRepository.findById(id).get();
    }

    //delete
    @Override
    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public Long rollDice(Player player) {
        int dice1 = (int) Math.floor(Math.random() * 6 + 1);
        int dice2 = (int) Math.floor(Math.random() * 6 + 1);
        int result = dice1 + dice2;

        boolean isWinner = isWinner(result);

        Long player_id = player.getId();
        Game newGame = new Game(dice1, dice2, result, isWinner, player_id);
        winAvg(player);
        this.addGame(newGame, player_id);
        player.addGame(newGame);
        //playerservice.updatePlayer(player);
        playerRepository.save(player);

        return newGame.getId();
    }

    @Override
    public void winAvg(Player player) {
        List<Game> gamesByPlayer = player.getGames();
        double winAvg = successAverage(gamesByPlayer);
        player.setWinnerAvg(winAvg);
    }

    @Override
    public boolean isWinner(int result) {
        return result == 7;
    }

    //gives a list of games of one Player
    @Override
    public List<Game> findAllGamesByPlayer(Long player_id) {
        return gameRepository.findAllByPlayer(player_id);
    }


    //PRIVATE METHODS
    //calculates the average of all games from one player
    public double successAverage(List<Game> successRolls) {
        double totalGames = successRolls.stream().map(Game::getResult).count();

        List<Game> success = successRolls.stream()
                .filter(s -> s.getResult() == 7)
                .collect(Collectors.toList());

        return success.size() / totalGames * 100;
    }
}