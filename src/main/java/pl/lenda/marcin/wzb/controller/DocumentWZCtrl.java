package pl.lenda.marcin.wzb.controller;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.lenda.marcin.wzb.dto.*;
import pl.lenda.marcin.wzb.entity.DocumentWz;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.repository.HistoryCorrectsDocumentRepository;
import pl.lenda.marcin.wzb.repository.HistoryDeleteDocumentWzRepository;
import pl.lenda.marcin.wzb.repository.ItemSearchRepository;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;
import pl.lenda.marcin.wzb.service.document_wz.DocumentWzServiceImplementation;
import pl.lenda.marcin.wzb.service.user_account.UserAccountService;

import java.util.*;

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
    protected final Logger log = (Logger) LoggerFactory.getLogger(getClass());

    private final Map<String, Object> response = new LinkedHashMap<>();

    @Autowired
    public DocumentWZCtrl(DocumentWzServiceImplementation documentWzServiceImplementation) {
        this.documentWzServiceImplementation = documentWzServiceImplementation;
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public
    @ResponseBody
    Collection search(@RequestParam String value) {
        return itemSearchRepository.searchItems(value);
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/saveDocument")
    public
    @ResponseBody
    Map<String, Object> createDocumentWz(@RequestBody DocumentWzDto documentWzDto) {
        log.debug("Wykonuje operację ABCDE");
        response.clear();

        System.out.println("data: " + documentWzDto.getDate());
        if (documentWzServiceImplementation.
                findByNumberWZAndSubProcess(documentWzDto.getNumberWZ(), documentWzDto.getSubProcess()) != null) {
            response.put("Error", "ExistsDocument");
            return response;
        } else {
            documentWzServiceImplementation.createDocumentWz(convertTo.convertDocumentToEntity(documentWzDto));
            response.put("Success", documentWzDto);
            return response;
        }

    }


    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/deleteDocument", method = RequestMethod.DELETE)
    public void deleteDocument(@RequestBody DocumentWzToDeleteDto documentWzToDeleteDto) {
        DocumentWz documentWz = documentWzServiceImplementation.findByNumberWZAndSubProcess(
                documentWzToDeleteDto.getNumberWZ(), documentWzToDeleteDto.getSubPro());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount userAccount = userAccountService.findByUsername(authentication.getName());

        //save history, who delete document
        historyDeleteDocumentWzRepository.save(convertTo.convertToHistoryDeleteDoc(documentWzToDeleteDto.getNumberWZ(), documentWzToDeleteDto.getSubPro(),
                documentWz.getClient(), documentWz.getTraderName(), userAccount.getUsername(), new Date()));
        documentWzServiceImplementation.removeDocumentWz(documentWz);
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/findByNumber", method = RequestMethod.POST)
    public
    @ResponseBody
    DocumentWzDto findDocumentWz(@RequestBody FindByNumberWzDto findByNumberWzDto) {
        DocumentWz byNumberWz = documentWzServiceImplementation.findByNumberWZAndSubProcess(
                findByNumberWzDto.getNumberWZ(), findByNumberWzDto.getSubPro());

        return convertTo.convertDocumentToDto(byNumberWz);
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/findByClient", method = RequestMethod.POST)
    public List<DocumentWzDto> findByClient(@RequestBody DocumentWzAbbreviationNameDto documentWzAbbreviationNameDto) {
        List<DocumentWz> listDocumentWZ;
        List<DocumentWzDto> listDocumentWzDto = new ArrayList<>();
        listDocumentWZ = documentWzServiceImplementation.findByAbbreviationName(documentWzAbbreviationNameDto.getAbbreviationName());

        for (DocumentWz documentWz : listDocumentWZ) {
            listDocumentWzDto.add(convertTo.convertDocumentToDto(documentWz));
        }
        return listDocumentWzDto;
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/findByClientNumber", method = RequestMethod.POST)
    public List<DocumentWzDto> findByClientNumber(@RequestBody FindClientNumber findClientNumber) {
        List<DocumentWz> listDocumentWZ;
        List<DocumentWzDto> listDocumentWzDto = new ArrayList<>();
        listDocumentWZ = documentWzServiceImplementation.findByNumberClient(findClientNumber.getFindClientNumber());

        for (DocumentWz documentWz : listDocumentWZ) {
            listDocumentWzDto.add(convertTo.convertDocumentToDto(documentWz));
        }
        return listDocumentWzDto;
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/findByTraderName", method = RequestMethod.POST)
    public List<DocumentWzDto> findByTraderName(@RequestBody String traderName) {
        List<DocumentWz> listDocumentWZ;
        List<DocumentWzDto> listDocumentWzDto = new ArrayList<>();
        listDocumentWZ = documentWzServiceImplementation.findByNameTrader(traderName);

        for (DocumentWz documentWz : listDocumentWZ) {
            listDocumentWzDto.add(convertTo.convertDocumentToDto(documentWz));
        }
        return listDocumentWzDto;
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/showAllDocuments", method = RequestMethod.GET)
    public List<DocumentWzDto> findAll() {
        List<DocumentWzDto> listDocumentWzDto = new ArrayList<>();
        List<DocumentWz> listDocumentWZ = documentWzServiceImplementation.showAllDocument();

        for (DocumentWz documentWz : listDocumentWZ) {

            listDocumentWzDto.add(convertTo.convertDocumentToDto(documentWz));
        }
        return listDocumentWzDto;
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/by_correct", method = RequestMethod.PATCH)
    public void correctBy(@RequestBody FindByNumberWzDto findByNumberWzDto) {
        DocumentWz byNumberWz = documentWzServiceImplementation.findByNumberWZAndSubProcess(
                findByNumberWzDto.getNumberWZ(), findByNumberWzDto.getSubPro());
        byNumberWz.setBeCorrects(true);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount userAccount = userAccountService.findByUsername(authentication.getName());

        //save information who accept correct document
        historyCorrectsDocumentRepository.save(convertTo.convertToHistoryCorrectDoc(byNumberWz.getNumberWZ(), byNumberWz.getSubProcess(),
                byNumberWz.getClient(), byNumberWz.getTraderName(), userAccount.getUsername(), new Date()));
        documentWzServiceImplementation.createDocumentWz(byNumberWz);
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/find_correct", method = RequestMethod.GET)
    public List<DocumentWz> findCorrectionDocument() {
        return documentWzServiceImplementation.listByCorrectionDocuments();
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/find_nameteam", method = RequestMethod.POST)
    public
    @ResponseBody
    List<DocumentWz> findByNameTeam(@RequestBody String nameTeam) {
        return documentWzServiceImplementation.findByNameTeam(nameTeam);
    }
}
