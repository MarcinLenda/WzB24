package pl.lenda.marcin.wzb.service.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lenda.marcin.wzb.dto.TraderAccountDto;
import pl.lenda.marcin.wzb.entity.TraderAccount;
import pl.lenda.marcin.wzb.exception.ClientException;
import pl.lenda.marcin.wzb.exception.TraderException;
import pl.lenda.marcin.wzb.service.client_account.ClientAccountService;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;
import pl.lenda.marcin.wzb.service.trader.TraderService;

/**
 * Created by Promar on 22.01.2017.
 */
@Component
public class ValidateTrader {

    @Autowired
    TraderService traderService;
    @Autowired
    ConvertTo convertTo;
    @Autowired
    ClientAccountService clientAccountService;

    public TraderAccount traderValidate(TraderAccountDto traderAccountDto){

        if(traderService.findByNumberTrader(traderAccountDto.getNumberTrader()).isPresent()){
            throw TraderException.traderNumberAlreadyExists();
        }

        return convertTo.convertToTraderEntity(traderAccountDto);
    }

    public TraderAccount traderAccountDataCheck(TraderAccount traderAccount, TraderAccountDto traderAccountDto) {

      if(!traderAccount.getNameTeam().equals(traderAccountDto.getNameTeam())){
          if(!clientAccountService.findByNameTeam(traderAccountDto.getNameTeam()).isPresent()){
              throw ClientException.nameTeamNotFound();
          }
      }

      if(traderAccountDto.getNewNumberTrader() != null){
          if(traderService.findByNumberTrader(traderAccountDto.getNewNumberTrader()).isPresent()){
              throw TraderException.traderNumberAlreadyExists();
          }else{
              traderAccount.setNumberTrader(traderAccountDto.getNewNumberTrader());
          }
      }

      traderAccount.setName(traderAccountDto.getName());
      traderAccount.setNameTeam(traderAccountDto.getNameTeam().toUpperCase());
      traderAccount.setSurname(traderAccountDto.getSurname());

      return traderAccount;
    }
}
