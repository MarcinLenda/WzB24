package pl.lenda.marcin.wzb.service.reserved_items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lenda.marcin.wzb.entity.ItemReservedUnnecessary;
import pl.lenda.marcin.wzb.repository.ItemsReservedUnnecessaryRepository;

/**
 * Created by Promar on 30.01.2017.
 */
@Service
public class ItemsReservedUnncessaryImplementationService implements ItemsReservedUnnecessaryService {

    @Autowired
    private ItemsReservedUnnecessaryRepository itemsReservedUnnecessaryRepository;

    @Override
    public void saveItems(ItemReservedUnnecessary itemReservedUnnecessary) {
        itemsReservedUnnecessaryRepository.save(itemReservedUnnecessary);
    }

    @Override
    public void delete(ItemReservedUnnecessary itemReservedUnnecessary) {
        itemsReservedUnnecessaryRepository.delete(itemReservedUnnecessary);
    }

    @Override
    public ItemReservedUnnecessary findItemUnnecessary(String numberPro, String subPro, String position) {
        return itemsReservedUnnecessaryRepository.findByNumberProAndSubProAndPosition(numberPro, subPro,
                position);
    }

}
