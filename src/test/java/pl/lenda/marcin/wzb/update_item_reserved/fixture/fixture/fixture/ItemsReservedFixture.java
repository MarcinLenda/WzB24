package pl.lenda.marcin.wzb.update_item_reserved.fixture.fixture.fixture;

import pl.lenda.marcin.wzb.entity.ItemsReserved;

import java.util.Date;

/**
 * Created by Promar on 01.02.2017.
 */
public class ItemsReservedFixture {

    public static ItemsReserved itemsReserved(){
        ItemsReserved itemsReserved = new ItemsReserved();
        itemsReserved.setPosition("1000");
        itemsReserved.setNameTeam("STA");
        itemsReserved.setStatusItem(false);
        itemsReserved.setSubPro("2");
        itemsReserved.setDateAccepted("22/11/17");
        itemsReserved.setClientName("Santech");
        itemsReserved.setContentItem("Rura spalinowa turbo 100");
        itemsReserved.setCreator("Kliber");
        itemsReserved.setDateUpdate(new Date());
        itemsReserved.setKbn("XAA14");
        itemsReserved.setNameTeamCDS("SCA");
        itemsReserved.setNumberFactory("112313");
        itemsReserved.setDetailsContentItem("Brak");
        itemsReserved.setActiveItemForTrader("12");
        itemsReserved.setProvider("Valvex");
        itemsReserved.setSection("I");
        return itemsReserved;
    }
}
