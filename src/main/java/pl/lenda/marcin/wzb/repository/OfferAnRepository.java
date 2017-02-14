package pl.lenda.marcin.wzb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.lenda.marcin.wzb.entity.OfferAn;

import java.util.List;

/**
 * Created by Promar on 09.02.2017.
 */
@Repository
public interface OfferAnRepository extends MongoRepository<OfferAn, String> {

    List<OfferAn> findByWaitingANTrue();

    List<OfferAn> findByWaitingANFalse();

    OfferAn findById(String id);
}
