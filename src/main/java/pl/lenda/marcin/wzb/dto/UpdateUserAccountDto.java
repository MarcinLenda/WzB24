package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 20.11.2016.
 */
public class UpdateUserAccountDto {


    private String username;

    private String newUsername;

    private String name;

    private String surname;

    private String numberUser;

    private String nameTeam;

    public UpdateUserAccountDto(){
    }

    public UpdateUserAccountDto(String username, String newUsername, String name, String surname,
                                String numberUser, String nameTeam) {
        this.username = username;
        this.newUsername = newUsername;
        this.name = name;
        this.surname = surname;
        this.numberUser = numberUser;
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

    public String getNumberUser() {
        return numberUser;
    }

    public void setNumberUser(String numberUser) {
        this.numberUser = numberUser;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }
}
