package pl.lenda.marcin.wzb.service.trader;

import pl.lenda.marcin.wzb.entity.TraderAccount;

import java.util.List;
import java.util.Optional;

/**
 * Created by Promar on 03.11.2016.
 */
public interface TraderService {

    TraderAccount createTrader(TraderAccount traderAccount);

    void deleteTrader(TraderAccount traderAccount);

    Optional<TraderAccount> findByTraderSurnameAndNumber(String surname , String numberTrader);

    Optional<TraderAccount> findBySurname(String surname);

    Optional<TraderAccount> findByNumberTrader(String numberTrader);

    List<TraderAccount> findAllTrader();
}
