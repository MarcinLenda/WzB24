package pl.lenda.marcin.wzb.service.convert_class;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lenda.marcin.wzb.dto.*;
import pl.lenda.marcin.wzb.entity.*;
import pl.lenda.marcin.wzb.service.client_account.ClientAccountService;
import pl.lenda.marcin.wzb.service.trader.TraderService;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

/**
 * Created by Promar on 02.11.2016.
 */
@Component
public class ConvertTo {


    private final ClientAccountService clientAccountService;
    private final TraderService traderService;


    @Autowired
    public ConvertTo(ClientAccountService clientAccountService, TraderService traderService) {
        this.clientAccountService = clientAccountService;
        this.traderService = traderService;
    }

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
        documentWzDto.setAbbreviationName(documentWz.getAbbreviationName());
        return documentWzDto;
    }

    public DocumentWz convertDocumentToEntity(DocumentWzDto documentWzDto){
        DocumentWz documentWz = new DocumentWz();
        ClientAccount clientAccount = clientAccountService.findByAbbreviationName(documentWzDto.getClient()).get();
        TraderAccount traderAccount = traderService.findBySurname(documentWzDto.getTraderName()).get();

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
        clientAccount.setAbbreviationName(clientAccountDto.getAbbreviationName());
        return clientAccount;
    }

    public ClientAccountDto convertClientEntityToDto(ClientAccount clientAccount){
        ClientAccountDto clientAccountDto = new ClientAccountDto();

        clientAccountDto.setNameTeam(clientAccount.getNameTeam());
        clientAccountDto.setName(clientAccount.getName());
        clientAccountDto.setAbbreviationName(clientAccount.getAbbreviationName());
        clientAccountDto.setNumberClient(clientAccount.getNumberClient());
        return clientAccountDto;
    }

    public UserAccount convertToUserAccountEntity(UserAccountDto userAccountDto){
        UserAccount userAccount = new UserAccount();

        userAccount.setName(userAccountDto.getName());
        userAccount.setSurname(userAccountDto.getSurname());
        userAccount.setUsername(userAccountDto.getUsername());
        userAccount.setPassword(userAccountDto.getPassword());
        userAccount.setNumberUser(userAccountDto.getNumberUser());
        return userAccount;
    }

    public UserAccountDto convertToUserAccountDto(UserAccount userAccount){
        UserAccountDto userAccountDto = new UserAccountDto();

        userAccountDto.setName(userAccount.getName());
        userAccountDto.setSurname(userAccount.getSurname());
        userAccountDto.setUsername(userAccount.getUsername());
        userAccountDto.setNumberUser(userAccount.getNumberUser());
        userAccountDto.setRole(userAccount.getRole());

        String teamName = traderService
                .findByTraderSurnameAndNumber(userAccount.getSurname(), userAccount.getNumberUser())
                .map(TraderAccount::getNameTeam).orElseGet(() -> "X");
        userAccountDto.setNameTeam(teamName);

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

    public TraderAccountDto convertToTraderDto(TraderAccount traderAccount){
        TraderAccountDto traderAccountDto = new TraderAccountDto();
        traderAccountDto.setName(traderAccount.getName());
        traderAccountDto.setSurname(traderAccount.getSurname());
        traderAccountDto.setNameTeam(traderAccount.getNameTeam());
        traderAccountDto.setNumberTrader(traderAccount.getNumberTrader());
        return traderAccountDto;
    }

    public HistoryDeleteDocumentWz convertToHistoryDeleteDoc(String number, String subPro, String client, String trader,
                                                             String username){

        HistoryDeleteDocumentWz historyDeleteDocumentWz = new HistoryDeleteDocumentWz();
        historyDeleteDocumentWz.setNumberWZ(number);
        historyDeleteDocumentWz.setSubPro(subPro);
        historyDeleteDocumentWz.setNameClient(client);
        historyDeleteDocumentWz.setNameTrader(trader);
        historyDeleteDocumentWz.setUser(username);
        historyDeleteDocumentWz.setDate(Instant.now());
        return historyDeleteDocumentWz;
    }

    public HistoryCorrectsDocument convertToHistoryCorrectDoc(String number, String subPro, String client, String trader,
                                                              String username){

        HistoryCorrectsDocument historyCorrectsDocument = new HistoryCorrectsDocument();
        historyCorrectsDocument.setNumberWZ(number);
        historyCorrectsDocument.setSubPro(subPro);
        historyCorrectsDocument.setUser(username);
        historyCorrectsDocument.setNameClient(client);
        historyCorrectsDocument.setNameTrader(trader);
        historyCorrectsDocument.setDate(new Date());
        return historyCorrectsDocument;
    }

    public OfferAn convertToOfferAnEntity(OfferAnDto offerAnDto, UserAccount userAccount){

        Optional<TraderAccount> traderAccount = traderService.findByNumberTrader(userAccount.getNumberUser());
        Optional<ClientAccount> clientAccount = clientAccountService.findByNumberClient(offerAnDto.getClient());

        OfferAn offerAnEntity = new OfferAn();


        if(clientAccount.isPresent()){
            offerAnEntity.setClient(clientAccount.get().getName());
        }else{
            offerAnEntity.setClient(offerAnDto.getClient());
        }

        if(traderAccount.isPresent()){
            offerAnEntity.setNameTeam(traderAccount.get().getNameTeam());
            offerAnEntity.setNameTrader(traderAccount.get().getSurname());
        }else{
            offerAnEntity.setNameTeam("Wolny");
            offerAnEntity.setNameTrader(userAccount.getSurname());
        }

        if(offerAnDto.getNumberOffer() == null){
            offerAnEntity.setNumberOffer("brak");
        }

        offerAnEntity.setContentPriority(offerAnDto.getContentPriority());
        offerAnEntity.setCityName(offerAnDto.getCityName());
        offerAnEntity.setNameOffer(offerAnDto.getNameOffer());
        offerAnEntity.setPriority(offerAnDto.getPriority());
        offerAnEntity.setWhoCreate(offerAnDto.getWhoCreate());
        offerAnEntity.setDateCreate(new Date());
        offerAnEntity.setFinishAN(offerAnDto.isFinishAN());
        offerAnEntity.setHistoryAN(offerAnDto.isHistoryAN());
        offerAnEntity.setWaitingAN(offerAnDto.isWaitingAN());
        offerAnEntity.setWhoCreate(offerAnDto.getWhoCreate());
        offerAnEntity.setPriority(offerAnDto.getPriority());
        offerAnEntity.setStatus(offerAnDto.getStatus());
        offerAnEntity.setValue(offerAnDto.getValue());

        return offerAnEntity;
    }
}
