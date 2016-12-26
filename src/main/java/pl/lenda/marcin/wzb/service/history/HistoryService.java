package pl.lenda.marcin.wzb.service.history;

import pl.lenda.marcin.wzb.entity.HistoryCorrectsDocument;
import pl.lenda.marcin.wzb.entity.HistoryDeleteDocumentWz;

import java.util.List;

/**
 * Created by Promar on 22.11.2016.
 */
public interface HistoryService {

    List<HistoryDeleteDocumentWz> showAllDeleteDocument();

    List<HistoryCorrectsDocument> showAllCorrectsDocument();
}
