package pl.lenda.marcin.wzb.service.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lenda.marcin.wzb.entity.HistoryLoggedAppIn;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.repository.HistoryLoggedInRepository;
import pl.lenda.marcin.wzb.repository.UserAccountRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Promar on 07.12.2016.
 */
@Service
public class HistoryLoggedInServiceImplementation implements HistoryLoggedInService {

    @Autowired
    HistoryLoggedInRepository historyLoggedInRepository;
    @Autowired
    UserAccountRepository userAccountRepository;


    @Override
    public void saveWhoLoggedIn(HistoryLoggedAppIn historyLoggedAppIn) {
        historyLoggedInRepository.save(historyLoggedAppIn);
    }

    @Override
    public List<HistoryLoggedAppIn> findByUsername(String username) {
        return historyLoggedInRepository.findByUsername(username);
    }

    @Override
    public List<HistoryLoggedAppIn> findLastLoggedIn() {
        List<HistoryLoggedAppIn> listAllHistoryLoggedUser = new ArrayList<>();
        List<UserAccount> allUserAccountList = userAccountRepository.findAll();
        List<HistoryLoggedAppIn> allUserListLastLogged = new ArrayList<>();

        for(int i = 0; i < allUserAccountList.size(); i++) {
            listAllHistoryLoggedUser = historyLoggedInRepository.findByUsername(allUserAccountList.get(i).getUsername());

            if(!listAllHistoryLoggedUser.isEmpty()){
               allUserListLastLogged.add(listAllHistoryLoggedUser.get(listAllHistoryLoggedUser.size() - 1));
            }
        }
        return allUserListLastLogged;
    }

    @Override
    public List<HistoryLoggedAppIn> findAll() {
        return historyLoggedInRepository.findAll();
    }
}
