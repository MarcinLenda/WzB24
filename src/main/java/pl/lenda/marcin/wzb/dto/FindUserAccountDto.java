package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 20.11.2016.
 */
public class FindUserAccountDto {

    private String username;

    public FindUserAccountDto(){
    }

    public FindUserAccountDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
