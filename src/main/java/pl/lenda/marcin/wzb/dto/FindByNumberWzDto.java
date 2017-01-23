package pl.lenda.marcin.wzb.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by Promar on 30.10.2016.
 */
public class FindByNumberWzDto {
    @NotNull
    private String numberWZ;
    @NotNull
    private String subPro;

    public FindByNumberWzDto(){
    }

    public String getNumberWZ() {
        return numberWZ;
    }

    public void setNumberWZ(String numberWZ) {
        this.numberWZ = numberWZ;
    }

    public String getSubPro() {
        return subPro;
    }

    public void setSubPro(String subPro) {
        this.subPro = subPro;
    }
}


