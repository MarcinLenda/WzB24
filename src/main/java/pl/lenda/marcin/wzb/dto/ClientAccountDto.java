package pl.lenda.marcin.wzb.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by Promar on 03.11.2016.
 */
public class ClientAccountDto {

    @NotNull
    private String name;
    @NotNull
    private String abbreviationName;
    @NotNull
    private String numberClient;

    private String newNumberClient;
    @NotNull
    private String nameTeam;

    private String newAbbreviationName;

    public ClientAccountDto(){
    }

    public ClientAccountDto(String name, String abbreviationName, String numberClient, String newNumberClient, String nameTeam, String newAbbreviationName) {
        this.name = name;
        this.abbreviationName = abbreviationName;
        this.numberClient = numberClient;
        this.newNumberClient = newNumberClient;
        this.nameTeam = nameTeam;
        this.newAbbreviationName = newAbbreviationName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberClient() {
        return numberClient;
    }

    public void setNumberClient(String numberClient) {
        this.numberClient = numberClient;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public String getAbbreviationName() {
        return abbreviationName;
    }

    public void setAbbreviationName(String abbreviationName) {
        this.abbreviationName = abbreviationName;
    }

    public String getNewAbbreviationName() {
        return newAbbreviationName;
    }

    public void setNewAbbreviationName(String newAbbreviationName) {
        this.newAbbreviationName = newAbbreviationName;
    }

    public String getNewNumberClient() {
        return newNumberClient;
    }

    public void setNewNumberClient(String newNumberClient) {
        this.newNumberClient = newNumberClient;
    }
}
