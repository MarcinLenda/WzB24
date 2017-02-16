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
import java.util.stream.Collectors;

/**
 * Created by Promar on 03.11.2016.
 */
@RestController
@RequestMapping("/client")
public class ClientCtrl {

    private final Map<String, Object> response = new LinkedHashMap<>();

    @Autowired
    private ClientAccountService clientAccountService;
    @Autowired
    private ConvertTo convertTo;
    @Autowired
    private ValidateClient validateClient;



    @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR"})
    @RequestMapping(value = "/save_client", method = RequestMethod.POST)
    @ResponseBody
    public void saveClient(@RequestBody @Valid ClientAccountDto clientAccountDto) {
        clientAccountService.createAccount(validateClient.clientValidate(clientAccountDto));
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    @RequestMapping(value = "/delete_client", method = RequestMethod.DELETE)
    public void deleteClient(@RequestBody ClientAccountDto clientFindDto){
        Optional<ClientAccount> clientAccount = clientAccountService.findByNumberClient(clientFindDto.getNumberClient());
        clientAccountService.deleteAccountClient(clientAccount);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR"})
    @RequestMapping(value = "/edit_client", method = RequestMethod.POST)
    public void editClient(@RequestBody ClientAccountDto clientAccountDto){
        ClientAccount clientAccount = clientAccountService.findByNumberClient(clientAccountDto.getNumberClient()).get();
       clientAccountService.createAccount(validateClient.clientAccountDataCheck(clientAccount, clientAccountDto));
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR"})
    @RequestMapping(value = "/all_client", method = RequestMethod.GET)
    public List<ClientAccountDto> allClientAccount() {
        return clientAccountService.findAllClient()
           .stream()
                .map(client -> convertTo.convertClientEntityToDto(client))
                .collect(Collectors.toList());
    }

}
