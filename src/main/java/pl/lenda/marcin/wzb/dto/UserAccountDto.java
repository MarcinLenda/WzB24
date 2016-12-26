package pl.lenda.marcin.wzb.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by Promar on 30.10.2016.
 */
public class UserAccountDto {

    @NotNull()
    private String username;
    @NotNull()
    private String name;
    @NotNull()
    private String surname;
    @NotNull()
    private String password;
    @NotNull()
    private String confirmPassword;
    @NotNull
    private String numberUser;
    @NotNull
    private String nameTeam;

    private String role;

    public UserAccountDto(){
    }

    public UserAccountDto(String username, String name, String surname, String password, String confirmPassword, String numberUser, String nameTeam, String role) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.numberUser = numberUser;
        this.nameTeam = nameTeam;
        this.role = role;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
}
