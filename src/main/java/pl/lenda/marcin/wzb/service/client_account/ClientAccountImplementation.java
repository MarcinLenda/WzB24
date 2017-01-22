package pl.lenda.marcin.wzb.service.client_account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lenda.marcin.wzb.entity.ClientAccount;
import pl.lenda.marcin.wzb.entity.DocumentWz;
import pl.lenda.marcin.wzb.exception.ClientException;
import pl.lenda.marcin.wzb.repository.ClientAccountRepository;
import pl.lenda.marcin.wzb.service.document_wz.DocumentWzService;

import java.util.List;
import java.util.Optional;

/**
 * Created by Promar on 03.11.2016.
 */
@Service
public class ClientAccountImplementation implements ClientAccountService {

    @Autowired
    ClientAccountRepository clientAccountRepository;
    @Autowired
    DocumentWzService documentWzService;

    @Override
    public ClientAccount createAccount(ClientAccount clientAccount) {
        return clientAccountRepository.save(clientAccount);
    }

    @Override
    public void deleteAccountClient(Optional<ClientAccount> clientAccount) {

        List<DocumentWz> documentWz = documentWzService.findByNumberClient(clientAccount.get().getNumberClient());

        if (clientAccount.isPresent()) {

            if (documentWz.isEmpty()) {
                clientAccountRepository.delete(clientAccount.get());
            } else {
                throw ClientException.clientHasDocumentWz();
            }


        } else {
            throw ClientException.clientNotFound();
        }
    }

    @Override
    public Optional<ClientAccount> findByAbbreviationName(String name) {
        return clientAccountRepository.findByAbbreviationNameIgnoreCase(name);
    }


    @Override
    public Optional<ClientAccount> findByNumberClient(String numberClient) {
        Optional<ClientAccount> clientAccount = clientAccountRepository.findByNumberClient(numberClient);
        return clientAccount;
    }

    @Override
    public Optional<ClientAccount> findByNameTeam(String nameTeam) {
        return clientAccountRepository.findByNameTeamIgnoreCase(nameTeam);
    }

    @Override
    public List<ClientAccount> findAllClient() {
        return clientAccountRepository.findAll();
    }
}
