package pl.lenda.marcin.wzb.service.client_account;

import pl.lenda.marcin.wzb.entity.ClientAccount;

import java.util.List;

/**
 * Created by Promar on 03.11.2016.
 */
public interface ClientAccountService {

    ClientAccount createAccount (ClientAccount clientAccount);

    void deleteAccountClient(ClientAccount clientAccount);

    ClientAccount findByAbbreviationName(String name);

    ClientAccount findByClientNameAndNumber(String name, String number);

    ClientAccount findByClientNumber(String numberClient);

    List<ClientAccount> findAllClient();
}
