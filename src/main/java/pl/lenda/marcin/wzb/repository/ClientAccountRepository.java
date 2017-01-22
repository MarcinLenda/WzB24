package pl.lenda.marcin.wzb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.lenda.marcin.wzb.entity.ClientAccount;

import java.util.Optional;

/**
 * Created by Promar on 03.11.2016.
 */
public interface ClientAccountRepository extends MongoRepository<ClientAccount, String> {

    Optional<ClientAccount> findByAbbreviationNameIgnoreCase(String name);

    Optional<ClientAccount> findByNumberClient(String numberClient);

    Optional<ClientAccount> findByNameTeamIgnoreCase(String nameTeam);

}
