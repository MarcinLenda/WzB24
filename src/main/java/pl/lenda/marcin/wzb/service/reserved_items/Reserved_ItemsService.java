package pl.lenda.marcin.wzb.service.reserved_items;

import pl.lenda.marcin.wzb.entity.ItemsReserved;

import java.util.List;

/**
 * Created by Promar on 26.11.2016.
 */
public interface Reserved_ItemsService {

    List<ItemsReserved> findAll();

    void saveItems(ItemsReserved _itemsReserved);

    boolean updateItems(ItemsReserved _itemsReserved);

    ItemsReserved findItem (String id);

    List<ItemsReserved> findByNameTeam(String nameTeam);

    void delete(ItemsReserved _itemsReserved);

    List<ItemsReserved> findAllItemsTrader(String nameTrader);
}
