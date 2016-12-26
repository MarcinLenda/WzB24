package pl.lenda.marcin.wzb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.lenda.marcin.wzb.entity.HistoryCorrectsDocument;

import java.util.List;

/**
 * Created by Promar on 25.11.2016.
 */
@Repository
public interface HistoryCorrectsDocumentRepository extends MongoRepository<HistoryCorrectsDocument, String> {

    List<HistoryCorrectsDocument> findAll();
}
