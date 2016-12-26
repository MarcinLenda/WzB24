package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 03.11.2016.
 */
public class ClientAccountDto {

    private String name;

    private String abbreviationName;

    private String numberClient;

    private String nameTeam;

    public ClientAccountDto(){
    }

    public ClientAccountDto(String name, String abbreviationName, String numberClient, String nameTeam) {
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
