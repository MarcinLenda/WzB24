package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 16.11.2016.
 */
public class TraderToDeleteDto {

    private String surname;

    private String numberTrader;

    public TraderToDeleteDto(){
    }

    public TraderToDeleteDto(String surname, String numberTrader) {
        this.surname = surname;
        this.numberTrader = numberTrader;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNumberTrader() {
        return numberTrader;
    }

    public void setNumberTrader(String numberTrader) {
        this.numberTrader = numberTrader;
    }
}
