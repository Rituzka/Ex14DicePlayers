package com.itAcademy.ex14diceplayer.model;


import java.io.Serializable;

public class Player extends AbstractEntity implements Serializable {


    private String username;

    public Player(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
