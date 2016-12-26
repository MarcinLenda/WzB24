package pl.lenda.marcin.wzb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.lenda.marcin.wzb.entity.HistoryLoggedAppIn;

import java.util.List;

/**
 * Created by Promar on 07.12.2016.
 */
@Repository
public interface HistoryLoggedInRepository extends MongoRepository<HistoryLoggedAppIn, String> {

    List<HistoryLoggedAppIn> findByUsername(String username);

    List<HistoryLoggedAppIn> findAll();
}
