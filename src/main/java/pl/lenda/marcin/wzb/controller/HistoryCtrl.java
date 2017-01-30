package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lenda.marcin.wzb.entity.HistoryCorrectsDocument;
import pl.lenda.marcin.wzb.entity.HistoryDeleteDocumentWz;
import pl.lenda.marcin.wzb.entity.HistoryLoggedAppIn;
import pl.lenda.marcin.wzb.service.history.HistoryLoggedInService;
import pl.lenda.marcin.wzb.service.history.HistoryService;

import java.util.List;

/**
 * Created by Promar on 22.11.2016.
 */
@RestController
public class HistoryCtrl {


    @Autowired
    HistoryService historyService;
    @Autowired
    HistoryLoggedInService historyLoggedInService;

    @CrossOrigin(origins = "http://155.133.24.148:8080")
    @RequestMapping(value = "/all_deleteE", method = RequestMethod.GET)
    public List<HistoryDeleteDocumentWz> showAllDocumentDelete(){
        return historyService.showAllDeleteDocument();
    }

    @CrossOrigin(origins = "http://155.133.24.148:8080")
    @RequestMapping(value = "/all_corrects", method = RequestMethod.GET)
    public List<HistoryCorrectsDocument> showAllDocumentCorrects(){
        return historyService.showAllCorrectsDocument();
    }

    @CrossOrigin(origins = "http://155.133.24.148:8080")
    @RequestMapping(value = "/all_logged", method = RequestMethod.GET)
    public List<HistoryLoggedAppIn> showAllLoggedUser(){
        return historyLoggedInService.findLastLoggedIn();
    }
}
