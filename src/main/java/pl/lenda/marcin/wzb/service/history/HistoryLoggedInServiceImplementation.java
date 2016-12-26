package pl.lenda.marcin.wzb.service.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lenda.marcin.wzb.entity.HistoryLoggedAppIn;
import pl.lenda.marcin.wzb.repository.HistoryLoggedInRepository;

import java.util.List;

/**
 * Created by Promar on 07.12.2016.
 */
@Service
public class HistoryLoggedInServiceImplementation implements HistoryLoggedInService {

    @Autowired
    HistoryLoggedInRepository historyLoggedInRepository;


    @Override
    public void saveWhoLoggedIn(HistoryLoggedAppIn historyLoggedAppIn) {
        historyLoggedInRepository.save(historyLoggedAppIn);
    }

    @Override
    public List<HistoryLoggedAppIn> findByUsername(String username) {
        return historyLoggedInRepository.findByUsername(username);
    }

    @Override
    public List<HistoryLoggedAppIn> findAll() {
        return historyLoggedInRepository.findAll();
    }
}
