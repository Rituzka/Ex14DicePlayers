package com.itAcademy.ex14diceplayer.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "games")
public class Game extends AbstractEntity implements Serializable {

    @Column(name = "dice_1")
    int dice1;
    @Column(name = "dice_2")
    int dice2;
    @Column
    int result;
    @Column(name = "winner")
    boolean isWinner;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Player player;


    //Constructor #1
    public Game(int dice1, int dice2, int result, boolean isWinner, Player player) {
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.result = result;
        this.isWinner = isWinner;
        this.player = player;
    }

    //Constructor #2
    public Game() {
    }

    //getters & setters

    public int getDice1() {
        return dice1;
    }

    public void setDice1(int dice1) {
        this.dice1 = dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public void setDice2(int dice2) {
        this.dice2 = dice2;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    @Override
    public String toString() {
        return "Game{" +
                "dice1=" + dice1 +
                ", dice2=" + dice2 +
                ", isWinner=" + isWinner +
                ", player=" + player +
                '}';
    }

}
