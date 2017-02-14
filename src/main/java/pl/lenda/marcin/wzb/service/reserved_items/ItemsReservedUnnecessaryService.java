package pl.lenda.marcin.wzb.service.reserved_items;

import pl.lenda.marcin.wzb.entity.ItemReservedUnnecessary;

/**
 * Created by Promar on 30.01.2017.
 */
public interface ItemsReservedUnnecessaryService {

   void saveItems(ItemReservedUnnecessary itemReservedUnnecessary);

   void delete(ItemReservedUnnecessary itemReservedUnnecessary);

   ItemReservedUnnecessary findItemUnnecessary(String numberPro, String subPro, String position);
}
