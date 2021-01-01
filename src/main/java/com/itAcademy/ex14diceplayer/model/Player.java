package com.itAcademy.ex14diceplayer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "players")
public class Player extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -5799651275963827478L;

    @Column(name = "player_username", unique = true, length = 50, columnDefinition = "default 'Anonymous'")
    String username;

    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    Date registrationDate;
    @Column
    Double winnerAvg;

    @OneToMany(mappedBy = "player")
    @JsonIgnore
    private List<Game> games;

    //Constructor #1

    public Player(String username) {
        this.username = verifyUsername(username);
        this.registrationDate = new Date(System.currentTimeMillis());

    }

    //Constructor #2
    public Player() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public void setGames(Game game) {
        this.games.add(game);
    }

    //put default username "Anonymous" in case finds an empty name
    private String verifyUsername(String username) {
        if (username.isEmpty()) username = "Anonymous";
        return username;
    }

    @Override
    public String toString() {
        return "Player{" +
                "username='" + username + '\'' +
                ", registrationDate=" + registrationDate +
                ", winnerAvg=" + winnerAvg +
                ", games=" + games +
                '}';
    }

    /* private String currentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return formatter.format(date);
    }*/
}
