package pl.lenda.marcin.wzb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Promar on 26.11.2016.
 */
@Document
public class ItemsReserved {

    @Id
    private String id;

    private String numberPro;

    private String subPro;

    private String creator;

    private String contentItem;

    private String detailsContentItem;

    private String clientName;

    private String traderName;

    private String nameTeam;

    private String nameTeamCDS;

    private String kbn;

    private String numberFactory;

    private String provider;

    private String businessSector;

    private String dateAccepted;

    private String pieces;

    private String activeItemForTrader;

    private String delay;

    private String allPrice;

    private String priceItem;

    private String section;

    private String position;

    private boolean statusItem;

    private Date dateUpdate;

    public ItemsReserved(){
    }

    public ItemsReserved(String numberPro, String subPro, String creator, String contentItem, String detailsContentItem, String clientName, String traderName, String nameTeam,
                         String nameTeamCDS, String kbn, String numberFactory, String provider, String businessSector, String dateAccepted, String pieces, String activeItemForTrader, String delay, String allPrice, String priceItem, String section, String position, boolean statusItem, Date dateUpdate) {
        this.numberPro = numberPro;
        this.subPro = subPro;
        this.creator = creator;
        this.contentItem = contentItem;
        this.detailsContentItem = detailsContentItem;
        this.clientName = clientName;
        this.traderName = traderName;
        this.nameTeam = nameTeam;
        this.nameTeamCDS = nameTeamCDS;
        this.kbn = kbn;
        this.numberFactory = numberFactory;
        this.provider = provider;
        this.businessSector = businessSector;
        this.dateAccepted = dateAccepted;
        this.pieces = pieces;
        this.activeItemForTrader = activeItemForTrader;
        this.delay = delay;
        this.allPrice = allPrice;
        this.priceItem = priceItem;
        this.section = section;
        this.position = position;
        this.statusItem = statusItem;
        this.dateUpdate = dateUpdate;
    }

    public String getNumberPro() {
        return numberPro;
    }

    public void setNumberPro(String numberPro) {
        this.numberPro = numberPro;
    }

    public String getContentItem() {
        return contentItem;
    }

    public void setContentItem(String contentItem) {
        this.contentItem = contentItem;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getBusinessSector() {
        return businessSector;
    }

    public void setBusinessSector(String businessSector) {
        this.businessSector = businessSector;
    }

    public String getDateAccepted() {
        return dateAccepted;
    }

    public void setDateAccepted(String dateAccepted) {
        this.dateAccepted = dateAccepted;
    }

    public String getKbn() {
        return kbn;
    }

    public void setKbn(String kbn) {
        this.kbn = kbn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActiveItemForTrader() {
        return activeItemForTrader;
    }

    public void setActiveItemForTrader(String activeItemForTrader) {
        this.activeItemForTrader = activeItemForTrader;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(String allPrice) {
        this.allPrice = allPrice;
    }

    public String getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(String priceItem) {
        this.priceItem = priceItem;
    }

    public String getSubPro() {
        return subPro;
    }

    public void setSubPro(String subPro) {
        this.subPro = subPro;
    }

    public String getDetailsContentItem() {
        return detailsContentItem;
    }

    public void setDetailsContentItem(String detailsContentItem) {
        this.detailsContentItem = detailsContentItem;
    }

    public String getNameTeamCDS() {
        return nameTeamCDS;
    }

    public void setNameTeamCDS(String nameTeamCDS) {
        this.nameTeamCDS = nameTeamCDS;
    }

    public String getNumberFactory() {
        return numberFactory;
    }

    public void setNumberFactory(String numberFactory) {
        this.numberFactory = numberFactory;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public boolean isStatusItem() {
        return statusItem;
    }

    public void setStatusItem(boolean statusItem) {
        this.statusItem = statusItem;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPieces() {
        return pieces;
    }

    public void setPieces(String pieces) {
        this.pieces = pieces;
    }
}
