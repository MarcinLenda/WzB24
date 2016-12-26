package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 10.11.2016.
 */
public class UserInfoDto {

    private String username;

    String name;

    String surname;

    String nameTeam;

   public UserInfoDto(){}

    public UserInfoDto(String username, String name, String surname, String nameTeam) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.nameTeam = nameTeam;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}


