package pl.lenda.marcin.wzb.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * Created by Promar on 30.01.2017.
 */
@Document
public class ItemReservedUnnecessary {

    @Id
    private String id;

    private String numberPro;

    private String subPro;

    private String position;

    public ItemReservedUnnecessary(){
    }

    public ItemReservedUnnecessary(String id, String numberPro, String subPro, String position) {
        this.id = id;
        this.numberPro = numberPro;
        this.subPro = subPro;
        this.position = position;
    }

    public String getNumberPro() {
        return numberPro;
    }

    public void setNumberPro(String numberPro) {
        this.numberPro = numberPro;
    }

    public String getSubPro() {
        return subPro;
    }

    public void setSubPro(String subPro) {
        this.subPro = subPro;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
