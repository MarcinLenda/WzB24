package pl.lenda.marcin.wzb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.lenda.marcin.wzb.entity.TraderAccount;

import java.util.Optional;

/**
 * Created by Promar on 03.11.2016.
 */
public interface TraderAccountRepository extends MongoRepository<TraderAccount, String> {

    Optional<TraderAccount> findBySurnameAndNumberTrader(String surname, String numberTrader);

    Optional<TraderAccount> findBySurname(String surname);

    Optional<TraderAccount> findByNumberTrader(String numberTrader);
}
