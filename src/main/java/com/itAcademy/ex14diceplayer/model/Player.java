package com.itAcademy.ex14diceplayer.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "players")
public class Player extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -5799651275963827478L;

    @Column(name = "player_username", unique = true)
    String username;

    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    Date registrationDate = new Date(System.currentTimeMillis());
    @Column
    Double winnerAvg;


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


    //put default username "Anonymous" in case finds an empty name
    private String verifyUsername(String username) {
        if (username.isEmpty()) username = "Anonymous";
        return username;
    }
}
