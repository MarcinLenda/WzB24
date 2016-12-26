package pl.lenda.marcin.wzb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.lenda.marcin.wzb.entity.ItemsReserved;

import java.util.List;

/**
 * Created by Promar on 26.11.2016.
 */
@Repository
public interface Reserved_ItemsRepository extends MongoRepository<ItemsReserved, String> {

    List<ItemsReserved> findAll();

    ItemsReserved findById(String id);

    void delete(ItemsReserved _itemsReserved);

    List<ItemsReserved> findByNameTeam(String nameTeam);

    List<ItemsReserved> findByCreator(String traderName);
}
