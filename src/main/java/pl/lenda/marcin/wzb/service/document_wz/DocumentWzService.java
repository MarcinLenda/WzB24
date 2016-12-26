package pl.lenda.marcin.wzb.service.document_wz;

import pl.lenda.marcin.wzb.entity.DocumentWz;

import java.util.List;

/**
 * Created by Promar on 28.10.2016.
 */
public interface DocumentWzService {

    DocumentWz createDocumentWz(DocumentWz documentWz);

    DocumentWz findByNumberWZAndSubProcess(String numberWZ, String subProcess);

    List<DocumentWz> findByAbbreviationName(String abbreviationName);

    List<DocumentWz> findByNumberClient(String numberClient);

    List<DocumentWz> findByNameClient(String nameClient);

    List<DocumentWz> findByNameTrader(String nameTrader);

    List<DocumentWz> findByNameTeam(String nameTeam);

    List<DocumentWz> showAllDocument();

    void removeDocumentWz(DocumentWz documentWz);

    List<DocumentWz> listByCorrectionDocuments();

}
