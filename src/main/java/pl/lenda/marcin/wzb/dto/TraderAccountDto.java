package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 03.11.2016.
 */
public class TraderAccountDto {

    private String name;

    private String surname;

    private String nameTeam;

    private String numberTrader;

    public TraderAccountDto(){
    }

    public TraderAccountDto(String name, String surname, String nameTeam, String numberTrader) {
        this.name = name;
        this.surname = surname;
        this.nameTeam = nameTeam;
        this.numberTrader = numberTrader;
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

    public String getNumberTrader() {
        return numberTrader;
    }

    public void setNumberTrader(String numberTrader) {
        this.numberTrader = numberTrader;
    }

    @Override
    public String toString() {
        return "TraderAccountDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nameTeam='" + nameTeam + '\'' +
                '}';
    }
}
