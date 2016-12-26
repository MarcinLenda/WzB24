package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 10.11.2016.
 */
public class UserNameDto {

    private String username;

    public UserNameDto(){}

    public UserNameDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
