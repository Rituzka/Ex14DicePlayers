package com.itAcademy.ex14diceplayer.model;

import java.io.Serializable;

public class Game extends AbstractEntity implements Serializable {

    private Player player;
    private int dice1;
    private int dice2;
    private boolean winner;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public Game(Player player, int dice1, int dice2, boolean winner) {
        this.player = player;
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.winner = winner;


    }
}
