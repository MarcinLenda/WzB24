package pl.lenda.marcin.wzb.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Promar on 09.02.2017.
 */
@Document
public class OfferAn {

    @Id
    private String id;

    private String numberOffer;

    private String nameOffer;

    private String nameTrader;

    private String nameTeam;

    private String client;

    private String cityName;

    private String adress;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date dateCreate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date dateFinish;

    private String status;

    private String priority;

    private String contentPriority;

    private String whoCreate;

    private String value;

    private boolean waitingAN;

    private boolean finishAN;

    private boolean historyAN;

    public OfferAn(){
    }

    public OfferAn(String id, String numberOffer, String nameOffer, String nameTrader, String nameTeam, String client,
                   String cityName, String adress, Date dateCreate, Date dateFinish,
                   String status, String priority, String contentPriority, String whoCreate, String value, boolean waitingAN, boolean finishAN, boolean historyAN) {
        this.id = id;
        this.numberOffer = numberOffer;
        this.nameOffer = nameOffer;
        this.nameTrader = nameTrader;
        this.nameTeam = nameTeam;
        this.client = client;
        this.cityName = cityName;
        this.adress = adress;
        this.dateCreate = dateCreate;
        this.dateFinish = dateFinish;
        this.status = status;
        this.priority = priority;
        this.contentPriority = contentPriority;
        this.whoCreate = whoCreate;
        this.value = value;
        this.waitingAN = waitingAN;
        this.finishAN = finishAN;
        this.historyAN = historyAN;
    }

    public String getNameOffer() {
        return nameOffer;
    }

    public void setNameOffer(String nameOffer) {
        this.nameOffer = nameOffer;
    }

    public String getNameTrader() {
        return nameTrader;
    }

    public void setNameTrader(String nameTrader) {
        this.nameTrader = nameTrader;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public boolean isWaitingAN() {
        return waitingAN;
    }

    public void setWaitingAN(boolean waitingAN) {
        this.waitingAN = waitingAN;
    }

    public boolean isFinishAN() {
        return finishAN;
    }

    public void setFinishAN(boolean finishAN) {
        this.finishAN = finishAN;
    }

    public boolean isHistoryAN() {
        return historyAN;
    }

    public void setHistoryAN(boolean historyAN) {
        this.historyAN = historyAN;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWhoCreate() {
        return whoCreate;
    }

    public void setWhoCreate(String whoCreate) {
        this.whoCreate = whoCreate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getNumberOffer() {return numberOffer;
    }

    public void setNumberOffer(String numberOffer) {this.numberOffer = numberOffer;}

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getContentPriority() {
        return contentPriority;
    }

    public void setContentPriority(String contentPriority) {
        this.contentPriority = contentPriority;
    }
}
