package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 03.12.2016.
 */
public class Reserved_ItemsFindStatistics {

    private String nameTeam;

    public Reserved_ItemsFindStatistics(){
    }

    public Reserved_ItemsFindStatistics(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }
}

