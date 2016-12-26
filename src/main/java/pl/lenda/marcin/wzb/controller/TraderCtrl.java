package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.lenda.marcin.wzb.dto.FindTraderAccount;
import pl.lenda.marcin.wzb.dto.TraderAccountDto;
import pl.lenda.marcin.wzb.dto.TraderToDeleteDto;
import pl.lenda.marcin.wzb.entity.TraderAccount;
import pl.lenda.marcin.wzb.repository.TraderAccountRepository;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;
import pl.lenda.marcin.wzb.service.trader.TraderService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Promar on 03.11.2016.
 */
@RestController
public class TraderCtrl {

    private final Map<String, Object> response = new LinkedHashMap<>();

    @Autowired
    private TraderService traderService;
    @Autowired
    private ConvertTo convertTo;
    @Autowired
    private TraderAccountRepository traderAccountRepository;


    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save_trader", method = RequestMethod.POST)
    public Map<String, Object> saveTrader(@RequestBody TraderAccountDto traderAccountDto){
        response.clear();
        if(traderService.findByTraderSurnameAndNumber(traderAccountDto.getSurname(),
                traderAccountDto.getNumberTrader())!= null){
            response.put("Error", "ExistsTrader");
            return response;
        }else if(traderService.findByNumberTrader(traderAccountDto.getNumberTrader()) != null){
            response.put("Error", "ExistsTrader");
            return response;
        }

        else{
            traderService.createTrader(convertTo.convertToTraderEntity(traderAccountDto));
            response.put("Success", traderAccountDto);
            return response;
        }
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/all_trader", method = RequestMethod.GET)
    public List<TraderAccount> findAllTrader(){
        List listTrader = new ArrayList();
        return listTrader = traderService.findAllTrader();
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/find_trader", method = RequestMethod.POST)
    public TraderAccount findTrader(@RequestBody FindTraderAccount findTraderAccount){
        TraderAccount traderAccount = traderService.findByTraderSurnameAndNumber(
                findTraderAccount.getSurname(), findTraderAccount.getNumberTrader());
        return traderAccount;
    }


    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete_trader", method = RequestMethod.DELETE)
    public void deleteTraderAccount(@RequestBody TraderToDeleteDto traderToDeleteDto){
        TraderAccount traderAccount = traderService.findByTraderSurnameAndNumber(traderToDeleteDto.getSurname(),
                traderToDeleteDto.getNumberTrader());

        traderService.deleteTrader(traderAccount);
    }
}
