package pl.lenda.marcin.wzb.service.convert_class;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lenda.marcin.wzb.dto.ClientAccountDto;
import pl.lenda.marcin.wzb.dto.DocumentWzDto;
import pl.lenda.marcin.wzb.dto.TraderAccountDto;
import pl.lenda.marcin.wzb.dto.UserAccountDto;
import pl.lenda.marcin.wzb.entity.*;
import pl.lenda.marcin.wzb.service.client_account.ClientAccountImplementation;
import pl.lenda.marcin.wzb.service.trader.TraderServiceImplementation;

import java.util.Date;

/**
 * Created by Promar on 02.11.2016.
 */
@Component
public class ConvertTo {

    @Autowired
    ClientAccountImplementation clientAccountImplementation;
    @Autowired
    TraderServiceImplementation traderServiceImplementation;

    public DocumentWzDto convertDocumentToDto(DocumentWz documentWz){
        DocumentWzDto documentWzDto = new DocumentWzDto();
        documentWzDto.setNumberWZ(documentWz.getNumberWZ());
        documentWzDto.setSubProcess(documentWz.getSubProcess());
        documentWzDto.setClient(documentWz.getClient());
        documentWzDto.setDate(documentWz.getDate());
        documentWzDto.setClientNumber(documentWz.getClientNumber());
        documentWzDto.setNameTeam(documentWz.getNameTeam());
        documentWzDto.setTraderName(documentWz.getTraderName());
        documentWzDto.setBeCorrects(documentWz.isBeCorrects());
        return documentWzDto;
    }

    public DocumentWz convertDocumentToEntity(DocumentWzDto documentWzDto){
        DocumentWz documentWz = new DocumentWz();
        ClientAccount clientAccount = clientAccountImplementation.findByAbbreviationName(documentWzDto.getClient());
        TraderAccount traderAccount = traderServiceImplementation.findBySurname(documentWzDto.getTraderName());

        documentWz.setNumberWZ(documentWzDto.getNumberWZ());
        documentWz.setSubProcess(documentWzDto.getSubProcess());
        documentWz.setClient(clientAccount.getName());
        documentWz.setDate(documentWzDto.getDate());
        documentWz.setTraderName(documentWzDto.getTraderName());
        documentWz.setBeCorrects(documentWzDto.isBeCorrects());
        documentWz.setNameTeam(traderAccount.getNameTeam());
        documentWz.setClientNumber(clientAccount.getNumberClient());
        documentWz.setAbbreviationName(clientAccount.getAbbreviationName());
        return documentWz;
    }

    public ClientAccount convertClientAccountDtoToEntity(ClientAccountDto clientAccountDto){
        ClientAccount clientAccount = new ClientAccount();
        clientAccount.setNameTeam(clientAccountDto.getNameTeam());
        clientAccount.setName(clientAccountDto.getName());
        clientAccount.setNumberClient(clientAccountDto.getNumberClient());
        return clientAccount;
    }

    public UserAccount converToUserAccountEntity(UserAccountDto userAccountDto){
        UserAccount userAccount = new UserAccount();
        userAccount.setName(userAccountDto.getName());
        userAccount.setSurname(userAccountDto.getSurname());
        userAccount.setUsername(userAccountDto.getUsername());
        userAccount.setPassword(userAccountDto.getPassword());
        userAccount.setNumberUser(userAccountDto.getNumberUser());
        return userAccount;
    }

    public UserAccountDto converToUserAccountDto(UserAccount userAccount){
        UserAccountDto userAccountDto = new UserAccountDto();
        userAccountDto.setName(userAccount.getName());
        userAccountDto.setSurname(userAccount.getSurname());
        userAccountDto.setUsername(userAccount.getUsername());
        userAccountDto.setPassword(userAccount.getPassword());
        userAccountDto.setNumberUser(userAccount.getNumberUser());
        return userAccountDto;
    }

    public TraderAccount convertToTraderEntity(TraderAccountDto traderAccountDto){
        TraderAccount traderAccount = new TraderAccount();
        traderAccount.setSurname(traderAccountDto.getSurname());
        traderAccount.setName(traderAccountDto.getName());
        traderAccount.setNameTeam(traderAccountDto.getNameTeam());
        traderAccount.setNumberTrader(traderAccountDto.getNumberTrader());
        return traderAccount;
    }

    public HistoryDeleteDocumentWz convertToHistoryDeleteDoc(String number, String subPro, String client, String trader,
                                                             String username, Date date){

        HistoryDeleteDocumentWz historyDeleteDocumentWz = new HistoryDeleteDocumentWz();
        historyDeleteDocumentWz.setNumberWZ(number);
        historyDeleteDocumentWz.setSubPro(subPro);
        historyDeleteDocumentWz.setNameClient(client);
        historyDeleteDocumentWz.setNameTrader(trader);
        historyDeleteDocumentWz.setUser(username);
        historyDeleteDocumentWz.setDate(new Date());
        return historyDeleteDocumentWz;
    }

    public HistoryCorrectsDocument convertToHistoryCorrectDoc(String number, String subPro, String client, String trader,
                                                              String username, Date date){

        HistoryCorrectsDocument historyCorrectsDocument = new HistoryCorrectsDocument();
        historyCorrectsDocument.setNumberWZ(number);
        historyCorrectsDocument.setSubPro(subPro);
        historyCorrectsDocument.setUser(username);
        historyCorrectsDocument.setNameClient(client);
        historyCorrectsDocument.setNameTrader(trader);
        historyCorrectsDocument.setDate(new Date());
        return historyCorrectsDocument;
    }
}
