package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.lenda.marcin.wzb.dto.FindTraderAccount;
import pl.lenda.marcin.wzb.dto.TraderAccountDto;
import pl.lenda.marcin.wzb.dto.TraderToDeleteDto;
import pl.lenda.marcin.wzb.entity.TraderAccount;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;
import pl.lenda.marcin.wzb.service.trader.TraderService;
import pl.lenda.marcin.wzb.service.validate.ValidateTrader;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Promar on 03.11.2016.
 */
@RestController
public class TraderCtrl {



    @Autowired
    private TraderService traderService;
    @Autowired
    private ValidateTrader validateTrader;
    @Autowired
    private ConvertTo convertTo;



    @CrossOrigin(origins = "http://155.133.24.148:8080")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save_trader", method = RequestMethod.POST)
    public void saveTrader(@RequestBody @Valid TraderAccountDto traderAccountDto){
        traderService.createTrader(validateTrader.traderValidate(traderAccountDto));
    }

    @CrossOrigin(origins = "http://155.133.24.148:8080")
    @RequestMapping(value = "/all_trader", method = RequestMethod.GET)
    public List<TraderAccountDto> findAllTrader(){
        return traderService.findAllTrader()
                .stream()
                .map(traderAccountDto -> convertTo.convertToTraderDto(traderAccountDto))
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://155.133.24.148:8080")
    @RequestMapping(value = "/find_trader", method = RequestMethod.POST)
    public TraderAccountDto findTrader(@RequestBody FindTraderAccount findTraderAccount){
        Optional<TraderAccount> traderAccount = traderService.findByTraderSurnameAndNumber(
                findTraderAccount.getSurname(), findTraderAccount.getNumberTrader());
        return convertTo.convertToTraderDto(traderAccount.get());
    }


    @CrossOrigin(origins = "http://155.133.24.148:8080")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete_trader", method = RequestMethod.DELETE)
    public void deleteTraderAccount(@RequestBody TraderToDeleteDto traderToDeleteDto){
        traderService
                .findByNumberTrader(traderToDeleteDto.getNumberTrader())
                .ifPresent(traderAccount -> traderService.deleteTrader(traderAccount));
    }


    @CrossOrigin(origins = "http://155.133.24.148:8080")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit_trader", method = RequestMethod.POST)
    public void editTrader(@RequestBody TraderAccountDto traderAccountDto){
        TraderAccount traderAccount = traderService.findByNumberTrader(traderAccountDto.getNumberTrader()).get();
        traderService.createTrader(validateTrader.traderAccountDataCheck(traderAccount, traderAccountDto));
    }
}
