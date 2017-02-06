package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.lenda.marcin.wzb.dto.*;
import pl.lenda.marcin.wzb.entity.DocumentWz;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.exception.DocumentWzException;
import pl.lenda.marcin.wzb.repository.HistoryCorrectsDocumentRepository;
import pl.lenda.marcin.wzb.repository.HistoryDeleteDocumentWzRepository;
import pl.lenda.marcin.wzb.repository.ItemSearchRepository;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;
import pl.lenda.marcin.wzb.service.document_wz.DocumentWzServiceImplementation;
import pl.lenda.marcin.wzb.service.user_account.UserAccountService;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Promar on 09.10.2016.
 */
@RestController
public class DocumentWZCtrl {

    private final DocumentWzServiceImplementation documentWzServiceImplementation;

    private FindByNumberWzDto findByNumberWzDto;
    private DocumentWzDto documentWzDto;
    private DocumentWz documentWz;

    @Autowired
    ItemSearchRepository itemSearchRepository;

    @Autowired
    private ConvertTo convertTo;
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    HistoryDeleteDocumentWzRepository historyDeleteDocumentWzRepository;
    @Autowired
    HistoryCorrectsDocumentRepository historyCorrectsDocumentRepository;


    @Autowired
    public DocumentWZCtrl(DocumentWzServiceImplementation documentWzServiceImplementation) {
        this.documentWzServiceImplementation = documentWzServiceImplementation;
    }

    @CrossOrigin(origins = "http://155.133.24.148:8080")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public
    @ResponseBody
    Collection search(@RequestParam String value) {
        return itemSearchRepository.searchItems(value);
    }

    @CrossOrigin(origins = "http://155.133.24.148:8080")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/saveDocument")
    public void createDocumentWz(@RequestBody @Valid DocumentWzDto documentWzDto) {
        Optional<DocumentWz> possibleDocument = documentWzServiceImplementation
                .findByNumberWZAndSubProcess(documentWzDto.getNumberWZ(), documentWzDto.getSubProcess());

        if (possibleDocument.isPresent()) {
            throw DocumentWzException.documentAlreadyExists();
        } else {
            documentWzServiceImplementation.createDocumentWz(convertTo.convertDocumentToEntity(documentWzDto));
        }
    }

    @CrossOrigin(origins = "http://155.133.24.148:8080")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/deleteDocument", method = RequestMethod.DELETE)
    public void deleteDocument(@RequestBody @Valid DocumentWzToDeleteDto documentWzToDeleteDto) {
        Optional<DocumentWz> possibleDocument = documentWzServiceImplementation.findByNumberWZAndSubProcess(
                documentWzToDeleteDto.getNumberWZ(), documentWzToDeleteDto.getSubPro());

        //w momencie gdy optional ma w sobie to metodda mapa zamieniamy sobie go na inny
        possibleDocument.map(document -> {

            documentWzServiceImplementation.removeDocumentWz(document);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserAccount userAccount = userAccountService.findByUsername(authentication.getName());

            //save history, who delete document
            historyDeleteDocumentWzRepository.save(convertTo.convertToHistoryDeleteDoc(documentWzToDeleteDto.getNumberWZ(),
                    documentWzToDeleteDto.getSubPro(),
                    document.getClient(), document.getTraderName(), userAccount.getUsername()));

            return document;
        })
                //referencja do metody ktora powinna byc wykonana w metodzie get suppliera
                .orElseThrow(DocumentWzException::documentNotFound);

    }

    @CrossOrigin(origins = "http://155.133.24.148:8080")
    @ResponseBody
    @RequestMapping(value = "/findByNumber", method = RequestMethod.POST)
    public DocumentWzDto findDocumentWz(@RequestBody @Valid FindByNumberWzDto findByNumberWzDto) {
        return documentWzServiceImplementation
                .findByNumberWZAndSubProcess(findByNumberWzDto.getNumberWZ(), findByNumberWzDto.getSubPro())
                .map(document -> convertTo.convertDocumentToDto(document))
                .orElseThrow(DocumentWzException::documentNotFound);
    }

    @CrossOrigin(origins = "http://155.133.24.148:8080")
    @RequestMapping(value = "/findByClient", method = RequestMethod.POST)
    public List<DocumentWzDto> findByClient(@RequestBody @Valid DocumentWzAbbreviationNameDto documentWzAbbreviationNameDto) {
        return documentWzServiceImplementation
                .findByAbbreviationName(documentWzAbbreviationNameDto.getAbbreviationName())
                .stream()
                .map(document -> convertTo.convertDocumentToDto(document))
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://155.133.24.148:8080")
    @RequestMapping(value = "/findByClientNumber", method = RequestMethod.POST)
    public List<DocumentWzDto> findByClientNumber(@RequestBody @Valid FindClientNumber findClientNumber) {
        return documentWzServiceImplementation.findByNumberClient(findClientNumber.getFindClientNumber())
                .stream()
                .map(documentWz -> convertTo.convertDocumentToDto(documentWz))
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://155.133.24.148:8080")
    @RequestMapping(value = "/findByTraderName", method = RequestMethod.POST)
    public List<DocumentWzDto> findByTraderName(@RequestBody @Valid String traderName) {
        return documentWzServiceImplementation.findByNameTrader(traderName)
                .stream()
                .map(documentWz -> convertTo.convertDocumentToDto(documentWz))
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://155.133.24.148:8080")
    @RequestMapping(value = "/showAllDocuments", method = RequestMethod.GET)
    public List<DocumentWzDto> findAll() {
        return documentWzServiceImplementation.showAllDocument()
                .stream()
                .map(documentWz -> convertTo.convertDocumentToDto(documentWz))
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://155.133.24.148:8080")
    @RequestMapping(value = "/by_correct", method = RequestMethod.PATCH)
    public void correctBy(@RequestBody @Valid FindByNumberWzDto findByNumberWzDto) {
        Optional<DocumentWz> possibleDocumentToCorrect = documentWzServiceImplementation.findByNumberWZAndSubProcess(
                findByNumberWzDto.getNumberWZ(), findByNumberWzDto.getSubPro());

        possibleDocumentToCorrect.map(documentWz ->{
            documentWz.setBeCorrects(true);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserAccount userAccount = userAccountService.findByUsername(authentication.getName());

            //save information who accept correct document
            historyCorrectsDocumentRepository.save(convertTo.convertToHistoryCorrectDoc(documentWz.getNumberWZ(), documentWz.getSubProcess(),
                    documentWz.getClient(), documentWz.getTraderName(), userAccount.getUsername()));
            documentWzServiceImplementation.createDocumentWz(documentWz);
            return documentWz;
        }).orElseThrow(DocumentWzException::documentNotFound);

    }

    @CrossOrigin(origins = "http://155.133.24.148:8080")
    @RequestMapping(value = "/find_correct", method = RequestMethod.GET)
    public List<DocumentWzDto> findCorrectionDocument() {
        return documentWzServiceImplementation
                .listByCorrectionDocuments()
                .stream()
                .map(documentWz -> convertTo.convertDocumentToDto(documentWz))
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://155.133.24.148:8080")
    @RequestMapping(value = "/find_nameteam", method = RequestMethod.POST)
    @ResponseBody
    public List<DocumentWzDto> findByNameTeam(@RequestBody @Valid String nameTeam) {
        return documentWzServiceImplementation
                .findByNameTeam(nameTeam)
                .stream()
                .map(documentWz -> convertTo.convertDocumentToDto(documentWz))
                .collect(Collectors.toList());
    }
}
