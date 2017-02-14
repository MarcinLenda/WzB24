package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 02.12.2016.
 */
public class ItemsReservedFindByDto {

    private String id;

    private String nameTrader;

    private String pieces;

    public ItemsReservedFindByDto(){
    }

    public ItemsReservedFindByDto(String id, String nameTrader, String pieces) {
        this.id = id;
        this.nameTrader = nameTrader;
        this.pieces = pieces;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPieces() {
        return pieces;
    }

    public void setPieces(String pieces) {
        this.pieces = pieces;
    }

    public String getNameTrader() {
        return nameTrader;
    }

    public void setNameTrader(String nameTrader) {
        this.nameTrader = nameTrader;
    }
}
