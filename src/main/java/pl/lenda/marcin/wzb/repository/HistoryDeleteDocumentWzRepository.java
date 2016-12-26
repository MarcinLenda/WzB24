package pl.lenda.marcin.wzb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.lenda.marcin.wzb.entity.HistoryDeleteDocumentWz;

import java.util.List;

/**
 * Created by Promar on 21.11.2016.
 */
@Repository
public interface HistoryDeleteDocumentWzRepository extends MongoRepository<HistoryDeleteDocumentWz, String> {

        List<HistoryDeleteDocumentWz> findAll();
}
