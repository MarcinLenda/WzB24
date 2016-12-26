package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 07.11.2016.
 */
public class UserAccountActiveOrRemoveDto {

    private String username;

    public UserAccountActiveOrRemoveDto(){
    }

    public UserAccountActiveOrRemoveDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
