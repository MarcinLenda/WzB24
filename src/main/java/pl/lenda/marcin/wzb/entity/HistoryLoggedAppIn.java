package pl.lenda.marcin.wzb.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Promar on 06.12.2016.
 */
@Document
public class HistoryLoggedAppIn {

    @Id
    String id;

    private String name;

    private String surname;

    private String username;

    private long howManyTimesLoggedIn;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date dateLogged;

    public HistoryLoggedAppIn() {
    }

    public HistoryLoggedAppIn(String name, String surname, String username, long howManyTimesLoggedIn, Date dateLogged) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.howManyTimesLoggedIn = howManyTimesLoggedIn;
        this.dateLogged = dateLogged;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getHowManyTimesLoggedIn() {
        return howManyTimesLoggedIn;
    }

    public void setHowManyTimesLoggedIn(long howManyTimesLoggedIn) {
        this.howManyTimesLoggedIn = howManyTimesLoggedIn;
    }

    public Date getDateLogged() {
        return dateLogged;
    }

    public void setDateLogged(Date dateLogged) {
        this.dateLogged = dateLogged;
    }
}


