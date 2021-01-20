package com.itAcademy.ex14diceplayer.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "games")
public class Game {

    @Id
    Long id;
    int dice1;
    int dice2;
    int result;
    boolean isWinner;
    Long player_id;


    //Constructor #1
    public Game(int dice1, int dice2, int result, boolean isWinner, Long player_id) {
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.result = result;
        this.isWinner = isWinner;
        this.player_id = player_id;
    }

    //Constructor #2
    public Game() {
    }

    //getters & setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Long player_id) {
        this.player_id = player_id;
    }


    @Override
    public String toString() {
        return "Game{" +
                "dice1=" + dice1 +
                ", dice2=" + dice2 +
                ", isWinner=" + isWinner +
                ", player_id=" + player_id+
                '}';
    }
}
