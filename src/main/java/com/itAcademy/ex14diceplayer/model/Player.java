package com.itAcademy.ex14diceplayer.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Document(collection = "players")
public class Player  {

    @Transient
    public static final String SEQUENCE_NAME = "id_sequence";

    @Id
    private Long id;
    private String username;
    Date registrationDate = new Date(System.currentTimeMillis());
    Double winnerAvg;
    List<Game> games;

    //Constructor #1

    public Player(String username) {
        this.username = verifyUsername(username);
        this.registrationDate = new Date(System.currentTimeMillis());
        this.winnerAvg = 0.00;
    }

    //Constructor #2
    protected Player() {
        this.games = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {this.username = username; }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Double getWinnerAvg() {
        return winnerAvg;
    }

    public void setWinnerAvg(Double winnerAvg) {
        this.winnerAvg = winnerAvg;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void addGame(Game game) {
        this.games.add(game);
    }

        //put default username "Anonymous" in case finds an empty name
    private String verifyUsername(String username) {
        if (username.isEmpty()) username = "Anonymous";
        return username;
    }
}
