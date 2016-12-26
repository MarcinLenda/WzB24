package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 17.11.2016.
 */
public class DocumentWzAbbreviationNameDto {

    private String abbreviationName;

    public DocumentWzAbbreviationNameDto(){
    }

    public DocumentWzAbbreviationNameDto(String abbreviationName, String clientNumber) {
        this.abbreviationName = abbreviationName;
    }

    public String getAbbreviationName() {
        return abbreviationName;
    }

    public void setAbbreviationName(String abbreviationName) {
        this.abbreviationName = abbreviationName;
    }

}
