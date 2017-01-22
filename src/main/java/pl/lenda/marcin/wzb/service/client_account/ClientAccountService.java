package pl.lenda.marcin.wzb.service.client_account;

import pl.lenda.marcin.wzb.entity.ClientAccount;

import java.util.List;
import java.util.Optional;

/**
 * Created by Promar on 03.11.2016.
 */
public interface ClientAccountService {

    ClientAccount createAccount (ClientAccount clientAccount);

    void deleteAccountClient(Optional<ClientAccount> clientAccount);

    Optional<ClientAccount> findByAbbreviationName(String name);

    Optional<ClientAccount> findByNumberClient(String numberClient);

    Optional<ClientAccount> findByNameTeam(String nameTeam);

    List<ClientAccount> findAllClient();
}
