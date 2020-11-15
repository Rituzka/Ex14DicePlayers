package com.itAcademy.ex14diceplayer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Random;


@Entity
@Table(name = "games")
public class Game extends AbstractEntity implements Serializable {

    @Column(name = "game_result")
    @NotNull
    private int result;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public Player player;


    //constructor #1 with parameter
    public Game(Player player) {
        int dice1 = rollDice();
        int dice2 = rollDice();
        this.result = dice1 + dice2;
        boolean winner = winGame(result);
        this.player = player;
    }
    //constructor #2 without parameters
    public Game(){}


    //getters & setters
    public int getDice1() {
        return rollDice();
    }

    public int getDice2() {
        return rollDice();
    }

    public int getResult() {
        return getDice1()+getDice2();
    }

    public boolean isWinner() {
        return winGame(getResult());
    }

    public Player getPlayer() {
        return player;
    }

    //roll the dice, random number
    public int rollDice(){
        Random r = new Random();
              return r.nextInt(6)+1;
    }

    public boolean winGame (int result) {
     this.result = result;
        return result == 7;
    }
    
    public double successAverage(List<Integer> results){
        return results.stream().mapToDouble(result -> result).average().getAsDouble();
    }


}
