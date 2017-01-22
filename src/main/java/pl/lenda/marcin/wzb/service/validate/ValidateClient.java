package pl.lenda.marcin.wzb.service.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lenda.marcin.wzb.dto.ClientAccountDto;
import pl.lenda.marcin.wzb.entity.ClientAccount;
import pl.lenda.marcin.wzb.exception.ClientException;
import pl.lenda.marcin.wzb.service.client_account.ClientAccountService;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;

/**
 * Created by Promar on 19.01.2017.
 */
@Component
public class ValidateClient {

    @Autowired
    ClientAccountService clientAccountService;
    @Autowired
    ConvertTo convertTo;


    public ClientAccount clientValidate(ClientAccountDto clientAccountDto) {

        if (clientAccountService.findByNumberClient(clientAccountDto.getNumberClient()).isPresent()) {
            throw ClientException.clientAlreadyExists();
        }

        if (clientAccountService.findByAbbreviationName(clientAccountDto.getAbbreviationName()).isPresent()) {
            throw ClientException.clientAlreadyExists();
        }
        return convertTo.convertClientAccountDtoToEntity(clientAccountDto);
    }

    public ClientAccount clientAccountDataCheck(ClientAccount clientAccount, ClientAccountDto clientAccountDto) {

        if (clientAccountDto.getNewNumberClient() != null) {
            if (clientAccountService.findByNumberClient(clientAccountDto.getNewNumberClient()).isPresent()) {
                throw ClientException.numberAlreadyExists();
            }
            clientAccount.setNumberClient(clientAccountDto.getNewNumberClient());
        }

        if (clientAccountDto.getNewAbbreviationName() != null) {
            if (clientAccountService.findByAbbreviationName(clientAccountDto.getAbbreviationName()).isPresent()) {
                throw ClientException.clientAlreadyExists();
            }
        }

        if(!clientAccountService.findByNameTeam(clientAccountDto.getNameTeam()).isPresent() ){
            throw ClientException.nameTeamNotFound();
        }

        clientAccount.setName(clientAccountDto.getName());
        clientAccount.setNameTeam(clientAccountDto.getNameTeam().toUpperCase());
        clientAccount.setAbbreviationName(clientAccountDto.getAbbreviationName());


        return clientAccount;
    }
}
