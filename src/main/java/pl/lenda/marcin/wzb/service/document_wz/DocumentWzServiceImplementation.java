package pl.lenda.marcin.wzb.service.document_wz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lenda.marcin.wzb.entity.DocumentWz;
import pl.lenda.marcin.wzb.repository.DocumentWzRepository;

import java.util.List;

/**
 * Created by Promar on 28.10.2016.
 */
@Service
public class DocumentWzServiceImplementation implements DocumentWzService {

    private final DocumentWzRepository documentWzRepository;


    @Autowired
    public DocumentWzServiceImplementation(DocumentWzRepository documentWzRepository) {
        this.documentWzRepository = documentWzRepository;
    }

    @Override
    public DocumentWz createDocumentWz(DocumentWz documentWz) {
        documentWzRepository.save(documentWz);
        return documentWz;
    }

    @Override
    public DocumentWz findByNumberWZAndSubProcess(String numberWZ, String subProcess) {
        return documentWzRepository.findByNumberWZAndSubProcess(numberWZ, subProcess);
    }

    @Override
    public List<DocumentWz> findByAbbreviationName(String abbreviationName) {
        return documentWzRepository.findByAbbreviationNameIgnoreCase(abbreviationName);
    }

    @Override
    public List<DocumentWz> findByNumberClient(String numberClient) {
        return documentWzRepository.findByClientNumber(numberClient);
    }

    @Override
    public List<DocumentWz> findByNameClient(String nameClient) {
        return documentWzRepository.findByClientIgnoreCase(nameClient);
    }

    @Override
    public List<DocumentWz> findByNameTrader(String nameTrader) {
        return documentWzRepository.findByTraderNameIgnoreCase(nameTrader);
    }

    @Override
    public List<DocumentWz> findByNameTeam(String nameTeam) {
        return documentWzRepository.findByNameTeamIgnoreCase(nameTeam);
    }

    @Override
    public List<DocumentWz> showAllDocument() {
        return documentWzRepository.findAll();
    }

    @Override
    public void removeDocumentWz(DocumentWz documentWz) {
        documentWzRepository.delete(documentWz);
    }

    @Override
    public List<DocumentWz> listByCorrectionDocuments() {
        return documentWzRepository.findByBeCorrectsTrue();
    }


}
