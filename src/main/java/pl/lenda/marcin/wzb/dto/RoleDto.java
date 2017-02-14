package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 01.02.2017.
 */
public class RoleDto {

    private String username;

    private String roleLevel;

    public RoleDto(String username, String roleLevel) {
        this.username = username;
        this.roleLevel = roleLevel;
    }

    public RoleDto(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(String roleLevel) {
        this.roleLevel = roleLevel;
    }
}
