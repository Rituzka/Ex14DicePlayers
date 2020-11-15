package com.itAcademy.ex14diceplayer.model;



import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "players")
public class Player extends AbstractEntity implements Serializable {

    @Column(name = "player_username")
    private String username;

    @Column(name = "player_registration")
    @NotNull
    private Date registrationDate;

    public Player(String username, Date registrationDate) {
        this.username = username;
        this.registrationDate = registrationDate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

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
