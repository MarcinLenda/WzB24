package pl.lenda.marcin.wzb.service.history;

import pl.lenda.marcin.wzb.entity.HistoryLoggedAppIn;

import java.util.List;

/**
 * Created by Promar on 07.12.2016.
 */
public interface HistoryLoggedInService {

    void saveWhoLoggedIn(HistoryLoggedAppIn historyLoggedAppIn);

    List<HistoryLoggedAppIn> findByUsername(String username);

    List<HistoryLoggedAppIn> findAll();

}
