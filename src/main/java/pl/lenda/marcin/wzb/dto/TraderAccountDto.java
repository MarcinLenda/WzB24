package pl.lenda.marcin.wzb.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by Promar on 03.11.2016.
 */
public class TraderAccountDto {
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String nameTeam;
    @NotNull
    private String numberTrader;

    private String newNumberTrader;

    public TraderAccountDto(){
    }

    public TraderAccountDto(String name, String surname, String nameTeam, String numberTrader, String newNumberTrader) {
        this.name = name;
        this.surname = surname;
        this.nameTeam = nameTeam;
        this.numberTrader = numberTrader;
        this.newNumberTrader = newNumberTrader;
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

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public String getNumberTrader() {
        return numberTrader;
    }

    public void setNumberTrader(String numberTrader) {
        this.numberTrader = numberTrader;
    }

    public String getNewNumberTrader() {
        return newNumberTrader;
    }

    public void setNewNumberTrader(String newNumberTrader) {
        this.newNumberTrader = newNumberTrader;
    }
}
