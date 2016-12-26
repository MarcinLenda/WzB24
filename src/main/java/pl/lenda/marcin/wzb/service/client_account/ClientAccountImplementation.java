package pl.lenda.marcin.wzb.service.client_account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lenda.marcin.wzb.entity.ClientAccount;
import pl.lenda.marcin.wzb.repository.ClientAccountRepository;

import java.util.List;

/**
 * Created by Promar on 03.11.2016.
 */
@Service
public class ClientAccountImplementation implements ClientAccountService {

    @Autowired
    ClientAccountRepository clientAccountRepository;

    @Override
    public ClientAccount createAccount(ClientAccount clientAccount) {
        return clientAccountRepository.save(clientAccount);
    }

    @Override
    public void deleteAccountClient(ClientAccount clientAccount) {
        clientAccountRepository.delete(clientAccount);
    }

    @Override
    public ClientAccount findByAbbreviationName(String name) {
        return clientAccountRepository.findByAbbreviationNameIgnoreCase(name);
    }

    @Override
    public ClientAccount findByClientNameAndNumber(String name, String number) {
        return clientAccountRepository.findByNameAndNumberClient(name, number);
    }

    @Override
    public ClientAccount findByClientNumber(String numberClient) {

        return clientAccountRepository.findByNumberClient(numberClient);
    }


    @Override
    public List<ClientAccount> findAllClient() {
        return clientAccountRepository.findAll();
    }
}
