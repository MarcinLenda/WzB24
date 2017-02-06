package pl.lenda.marcin.wzb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.lenda.marcin.wzb.entity.ItemReservedUnnecessary;

/**
 * Created by Promar on 30.01.2017.
 */
@Repository
public interface ItemsReservedUnnecessaryRepository extends MongoRepository<ItemReservedUnnecessary, String> {

    void delete(ItemReservedUnnecessary itemReservedUnnecessary);

    ItemReservedUnnecessary findByNumberProAndSubProAndPosition(String numberPro, String subPro,
                                                                String position);


}
