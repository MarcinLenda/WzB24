package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.lenda.marcin.wzb.dto.ClientAccountDto;
import pl.lenda.marcin.wzb.entity.ClientAccount;
import pl.lenda.marcin.wzb.service.client_account.ClientAccountService;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;
import pl.lenda.marcin.wzb.service.validate.ValidateClient;

import javax.validation.Valid;
import java.util.*;

/**
 * Created by Promar on 03.11.2016.
 */
@RestController
public class ClientCtrl {

    private final Map<String, Object> response = new LinkedHashMap<>();

    @Autowired
    private ClientAccountService clientAccountService;
    @Autowired
    private ConvertTo convertTo;
    @Autowired
    private ValidateClient validateClient;



    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save_client", method = RequestMethod.POST)
    @ResponseBody
    public void saveClient(@RequestBody @Valid ClientAccountDto clientAccountDto) {
        clientAccountService.createAccount(validateClient.clientValidate(clientAccountDto));
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete_client", method = RequestMethod.DELETE)
    public void deleteClient(@RequestBody ClientAccountDto clientFindDto){
        Optional<ClientAccount> clientAccount = clientAccountService.findByNumberClient(clientFindDto.getNumberClient());
        clientAccountService.deleteAccountClient(clientAccount);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit_client", method = RequestMethod.POST)
    public void editClient(@RequestBody ClientAccountDto clientAccountDto){
        ClientAccount clientAccount = clientAccountService.findByNumberClient(clientAccountDto.getNumberClient()).get();
       clientAccountService.createAccount(validateClient.clientAccountDataCheck(clientAccount, clientAccountDto));
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/all_client", method = RequestMethod.GET)
    public List<ClientAccount> allClientAccount() {
        List<ClientAccount> listClient = new ArrayList<>();
        listClient = clientAccountService.findAllClient();
        return listClient;
    }

}
