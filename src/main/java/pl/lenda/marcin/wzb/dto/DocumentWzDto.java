package pl.lenda.marcin.wzb.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Promar on 30.10.2016.
 */

public class DocumentWzDto {


    @NotNull(message = "nie moze byc puste")
    private String numberWZ;

    @NotNull(message = "nie moze byc subprocess")
    private String subProcess;

    @NotNull(message = "nie moze byc client")
    private String client;

    private String clientNumber;

    private String abbreviationName;

    private String nameTeam;

    private String traderName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;

    private boolean beCorrects;


    public DocumentWzDto() {
    }

    public DocumentWzDto(String numberWZ, String subProcess, String client, String clientNumber,
                         String abbreviationName, String nameTeam, String traderName, Date date, boolean beCorrects) {
        this.numberWZ = numberWZ;
        this.subProcess = subProcess;
        this.client = client;
        this.clientNumber = clientNumber;
        this.abbreviationName = abbreviationName;
        this.nameTeam = nameTeam;
        this.traderName = traderName;
        this.date = date;
        this.beCorrects = beCorrects;
    }

    public String getNumberWZ() {
        return numberWZ;
    }

    public void setNumberWZ(String numberWZ) {
        this.numberWZ = numberWZ;
    }

    public String getSubProcess() {
        return subProcess;
    }

    public void setSubProcess(String subProcess) {
        this.subProcess = subProcess;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isBeCorrects() {
        return beCorrects;
    }

    public void setBeCorrects(boolean beCorrects) {
        this.beCorrects = beCorrects;
    }

    public String getAbbreviationName() {
        return abbreviationName;
    }

    public void setAbbreviationName(String abbreviationName) {
        this.abbreviationName = abbreviationName;
    }
}
