package pl.lenda.marcin.wzb.service.reserved_items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lenda.marcin.wzb.entity.ItemsReserved;
import pl.lenda.marcin.wzb.repository.Reserved_ItemsRepository;

import java.util.List;

/**
 * Created by Promar on 26.11.2016.
 */
@Service
public class Reserved_ItemsServiceImplementation implements Reserved_ItemsService {

    @Autowired
    Reserved_ItemsRepository reserved_itemsRepository;


    @Override
    public List<ItemsReserved> findAll() {
        return reserved_itemsRepository.findAll();
    }

    @Override
    public void saveItems(ItemsReserved _itemsReserved) {
        reserved_itemsRepository.save(_itemsReserved);
    }

    @Override
    public boolean updateItems(ItemsReserved _itemsReserved) {
        reserved_itemsRepository.save(_itemsReserved);
        return true;
    }

    @Override
    public ItemsReserved findItem(String id) {
        return reserved_itemsRepository.findById(id);
    }

    @Override
    public List<ItemsReserved> findByNameTeam(String nameTeam) {
        return reserved_itemsRepository.findByNameTeam(nameTeam);
    }

    @Override
    public void delete(ItemsReserved _itemsReserved) {
        reserved_itemsRepository.delete(_itemsReserved);
    }

    @Override
    public List<ItemsReserved> findAllItemsTrader(String nameTrader) {
        return reserved_itemsRepository.findByCreator(nameTrader);
    }
}
