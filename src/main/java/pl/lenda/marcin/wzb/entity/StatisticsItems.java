package pl.lenda.marcin.wzb.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * Created by Promar on 03.12.2016.
 */
@Document
public class StatisticsItems {

    @Id
    private String id;

    private String nameTeam;

    private Integer last30Days;

    private Integer last60Days;

    private Integer last90Days;

    private Integer last180Days;

    private Integer lastYear;

    private String allPieces;

    private String allSum;

    public StatisticsItems(){
    }

    public StatisticsItems(String id, String nameTeam, Integer last30Days, Integer last60Days,
                           Integer last90Days, Integer last180Days, Integer lastYear, String allPieces, String allSum) {
        this.id = id;
        this.nameTeam = nameTeam;
        this.last30Days = last30Days;
        this.last60Days = last60Days;
        this.last90Days = last90Days;
        this.last180Days = last180Days;
        this.lastYear = lastYear;
        this.allPieces = allPieces;
        this.allSum = allSum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public Integer getLast30Days() {
        return last30Days;
    }

    public void setLast30Days(Integer last30Days) {
        this.last30Days = last30Days;
    }

    public Integer getLast60Days() {
        return last60Days;
    }

    public void setLast60Days(Integer last60Days) {
        this.last60Days = last60Days;
    }

    public Integer getLast90Days() {
        return last90Days;
    }

    public void setLast90Days(Integer last90Days) {
        this.last90Days = last90Days;
    }

    public Integer getLast180Days() {
        return last180Days;
    }

    public void setLast180Days(Integer last180Days) {
        this.last180Days = last180Days;
    }

    public Integer getLastYear() {
        return lastYear;
    }

    public void setLastYear(Integer lastYear) {
        this.lastYear = lastYear;
    }

    public String getAllPieces() {
        return allPieces;
    }

    public void setAllPieces(String allPieces) {
        this.allPieces = allPieces;
    }

    public String getAllSum() {
        return allSum;
    }

    public void setAllSum(String allSum) {
        this.allSum = allSum;
    }
}
