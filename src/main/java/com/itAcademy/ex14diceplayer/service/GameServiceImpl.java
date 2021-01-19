package com.itAcademy.ex14diceplayer.service;

import com.itAcademy.ex14diceplayer.model.Game;
import com.itAcademy.ex14diceplayer.model.Player;
import com.itAcademy.ex14diceplayer.model.sequencegenerator.SequenceGeneratorService;
import com.itAcademy.ex14diceplayer.repository.IGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class GameServiceImpl implements IGameService {

    @Autowired
    private IGameRepository IGameRepository;
    @Autowired
    private PlayerServiceImpl playerservice;
    @Autowired
    private SequenceGeneratorService sequenceServ;


    //Add a game
    @Override
    public void addGame(Game game) {
        game.setId(sequenceServ.generateSequence(Player.SEQUENCE_NAME));
        IGameRepository.save(game);
    }

    //find a game by id
    @Override
    public Game findGameById(Long id) {
        return IGameRepository.findById(id).get();
    }

    //gives a list of games of one Player
    @Override
    public List<Game> findAllGamesByPlayer(Long player_id) {
        return IGameRepository.findAllByPlayer(player_id);
    }

    //delete
    @Override
    public void deleteById(Long id) {
        IGameRepository.deleteById(id);
    }

    @Override
    public void deleteAllGames() {
        IGameRepository.deleteAll();
    }

    @Override
    public Long rollDice(Player player) {
        int dice1 = (int) Math.floor(Math.random() * 6 + 1);
        int dice2 = (int) Math.floor(Math.random() * 6 + 1);
        int result = dice1 + dice2;

        boolean isWinner = isWinner(result);

        Long player_id = player.getId();
        Game newGame = new Game(dice1, dice2, result, isWinner, player);
        addGame(newGame);
        winAvg(player);
        playerservice.updatePlayer(player);

        return newGame.getId();
    }

    @Override
    public void winAvg(Player player) {
        List<Game> gamesWon = IGameRepository.findAllByPlayer(player.getId());
        double winAvg = successAverage(gamesWon);
        player.setWinnerAvg(winAvg);
    }

    @Override
    public boolean isWinner(int result) {
        return result == 7;
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