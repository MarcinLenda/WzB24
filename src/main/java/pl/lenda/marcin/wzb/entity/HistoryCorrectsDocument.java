package pl.lenda.marcin.wzb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Promar on 25.11.2016.
 */
@Document
public class HistoryCorrectsDocument {

    @Id
    private String id;

    private String numberWZ;

    private String subPro;

    private String nameClient;

    private String nameTrader;

    private String user;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;

    public HistoryCorrectsDocument(){
    }

    public HistoryCorrectsDocument(String id, String numberWZ, String subPro, String nameClient, String nameTrader, String user, Date date) {
        this.id = id;
        this.numberWZ = numberWZ;
        this.subPro = subPro;
        this.nameClient = nameClient;
        this.nameTrader = nameTrader;
        this.user = user;
        this.date = date;
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

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getNameTrader() {
        return nameTrader;
    }

    public void setNameTrader(String nameTrader) {
        this.nameTrader = nameTrader;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
