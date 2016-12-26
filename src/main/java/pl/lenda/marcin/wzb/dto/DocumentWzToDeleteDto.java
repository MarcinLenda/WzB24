package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 02.11.2016.
 */
public class DocumentWzToDeleteDto {

    private String numberWZ;

    private String subPro;

    public DocumentWzToDeleteDto() {
    }

    public DocumentWzToDeleteDto(String numberWZ, String subPro) {
        this.numberWZ = numberWZ;
        this.subPro = subPro;
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
