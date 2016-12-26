package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 16.11.2016.
 */
public class ClientFindDto {

    private String name;

    private String number;

    public ClientFindDto(){
    }

    public ClientFindDto(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
