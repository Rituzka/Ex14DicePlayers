package com.itAcademy.ex14diceplayer.model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "players")
public class Player extends AbstractEntity implements Serializable {

    @Column(name = "player_username", unique = true)
    private String username;
    @Column(name = "registration_date")
    private final String registrationDate = currentDate();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "player")
    private final Set<Game> games = new HashSet<>();

    public Player(String username) {
        this.username = username;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Game> getGames() { return games; }


    private String currentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return formatter.format(date);
    }
}
