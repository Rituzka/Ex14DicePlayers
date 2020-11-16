package com.itAcademy.ex14diceplayer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "games")
public class Game extends AbstractEntity implements Serializable {

    @Column(name = "dice_1")
    private final int dice1 = rollDice();
    @Column(name = "dice_2")
    private final int dice2 = rollDice();
    @Column(name = "result")
    private final int result = dice1+dice2;
    @Column(name = "winner")
    private final boolean winner = winGame(result);

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Player player;

    //constructor #1 with parameter
    public Game(Player player) {
        this.player = player;
    }
    //constructor #2 without parameters
    public Game(){}


    //getters & setters
    public int getDice1() {
        return dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public int getResult() {
        return result;
    }

    public boolean isWinner() {
        return winner;
    }

    public Player getPlayer() {
        return player;
    }


    private int rollDice(){
       return (int) Math.floor(Math.random()*6+1);
    }

    private boolean winGame (int result) {
        return result == 7;
    }
    
    public String successAverage(List<Game> successRolls){
      double totalGames = successRolls.stream().map(Game::getResult).count();

       List<Game> success = successRolls.stream()
               .filter(s -> s.getResult() == 7)
               .collect(Collectors.toList());

        return roundDecimals(success.size()/totalGames);

    }

    private String roundDecimals(double number){
        DecimalFormat formatter = new DecimalFormat("###.##%");
         return formatter.format(number);
    }

}
