package pl.lenda.marcin.wzb.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by Promar on 13.11.2016.
 */
public class FindClientNumber {

    @NotNull
    private String findClientNumber;

    public FindClientNumber(){
    }

    public FindClientNumber(String findClientNumber) {
        this.findClientNumber = findClientNumber;
    }

    public String getFindClientNumber() {
        return findClientNumber;
    }

    public void setFindClientNumber(String findClientNumber) {
        this.findClientNumber = findClientNumber;
    }
}
