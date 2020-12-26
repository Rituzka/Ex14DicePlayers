package com.itAcademy.ex14diceplayer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name = "players")
public class Player extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "player_username", unique = true, length = 50)
    private String username;
    @Column(name = "registration_date", length = 50)
    private final String registrationDate = currentDate();

    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "player")
    //private final Set<Game> games = new HashSet<>();


    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    private String currentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return formatter.format(date);
    }
}
