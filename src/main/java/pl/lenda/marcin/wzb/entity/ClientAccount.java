package pl.lenda.marcin.wzb.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * Created by Promar on 03.11.2016.
 */
@Document
public class ClientAccount {

    @Id
    private String id;

    private String name;

    private String abbreviationName;

    private String numberClient;

    private String nameTeam;

    public ClientAccount() {
    }

    public ClientAccount(String name, String abbreviationName, String numberClient, String nameTeam) {
        this.name = name;
        this.abbreviationName = abbreviationName;
        this.numberClient = numberClient;
        this.nameTeam = nameTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
