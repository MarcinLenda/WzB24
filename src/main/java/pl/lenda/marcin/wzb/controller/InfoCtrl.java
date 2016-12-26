package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lenda.marcin.wzb.service.client_account.ClientAccountImplementation;
import pl.lenda.marcin.wzb.service.document_wz.DocumentWzServiceImplementation;
import pl.lenda.marcin.wzb.service.user.UserServiceImplementation;

/**
 * Created by Promar on 11.10.2016.
 */
@RestController
public class InfoCtrl {

    private final DocumentWzServiceImplementation documentWzServiceImplementation;
    private final UserServiceImplementation userServiceImplementation;
    private final ClientAccountImplementation clientAccountImplementation;

    @Autowired
    public InfoCtrl(DocumentWzServiceImplementation documentWzServiceImplementation, UserServiceImplementation userServiceImplementation, ClientAccountImplementation clientAccountImplementation) {
        this.documentWzServiceImplementation = documentWzServiceImplementation;
        this.userServiceImplementation = userServiceImplementation;
        this.clientAccountImplementation = clientAccountImplementation;
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/howManyTraders", method = RequestMethod.GET)
    public Integer howManyTraders() {
        return userServiceImplementation.findAllUser().size();
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/howManyDocument", method = RequestMethod.GET)
    public Integer howManyDocument() {
        return documentWzServiceImplementation.showAllDocument().size();
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/howManyClient", method = RequestMethod.GET)
    public Integer howManyClient(){
        return clientAccountImplementation.findAllClient().size();
    }
}
