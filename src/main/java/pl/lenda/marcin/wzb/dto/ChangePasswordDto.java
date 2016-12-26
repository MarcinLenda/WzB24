package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 10.11.2016.
 */
public class ChangePasswordDto {

    private String oldPassword;

    private String newPassword;

    private String confirmNewPassword;

    private String username;

    public ChangePasswordDto(){}

    public ChangePasswordDto(String oldPassword, String newPassword, String confirmNewPassword, String username) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmNewPassword = confirmNewPassword;
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
