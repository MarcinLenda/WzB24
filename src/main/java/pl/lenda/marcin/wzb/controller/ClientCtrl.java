package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.lenda.marcin.wzb.dto.ClientFindDto;
import pl.lenda.marcin.wzb.entity.ClientAccount;
import pl.lenda.marcin.wzb.service.client_account.ClientAccountService;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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


    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save_client", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> saveClient(@RequestBody ClientAccount clientAccount) {
        response.clear();
        if (clientAccountService.findByClientNumber(clientAccount.getNumberClient()) != null) {
            response.put("Error", "ExistsClientNr");
            return response;
        } else if (clientAccountService.findByAbbreviationName(clientAccount.getName()) != null) {
            response.put("Error", "ExistsClient");
            return response;
        } else if (clientAccount.getNumberClient().length() > 6 || clientAccount.getNumberClient().length() < 6) {
            response.put("Error", "NumberLength");
            return response;
        }else if(clientAccountService.findByAbbreviationName(clientAccount.getAbbreviationName()) != null){
            response.put("Error", "ExistsAbbreviation");
            return response;
        }
        else {
            clientAccountService.createAccount(clientAccount);
            response.put("Success", clientAccount);
            return response;
        }

    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete_client", method = RequestMethod.DELETE)
    public void deleteClient(@RequestBody ClientFindDto clientFindDto){
        ClientAccount clientAccount = clientAccountService.findByClientNameAndNumber(clientFindDto.getName(),
               clientFindDto.getNumber());
        clientAccountService.deleteAccountClient(clientAccount);
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/all_client", method = RequestMethod.GET)
    public List<ClientAccount> allClientAccount() {
        List<ClientAccount> listClient = new ArrayList<>();
        listClient = clientAccountService.findAllClient();
        return listClient;
    }

}
