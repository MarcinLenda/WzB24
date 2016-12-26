package pl.lenda.marcin.wzb.service.trader;

import pl.lenda.marcin.wzb.entity.TraderAccount;

import java.util.List;

/**
 * Created by Promar on 03.11.2016.
 */
public interface TraderService {

    TraderAccount createTrader(TraderAccount traderAccount);

    void deleteTrader(TraderAccount traderAccount);

    TraderAccount findByTraderSurnameAndNumber(String surname , String numberTrader);

    TraderAccount findBySurname(String surname);

    TraderAccount findByNumberTrader(String numberTrader);

    List<TraderAccount> findAllTrader();
}
