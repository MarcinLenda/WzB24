package pl.lenda.marcin.wzb.service.offer_an;

import pl.lenda.marcin.wzb.dto.OfferAnDto;
import pl.lenda.marcin.wzb.entity.OfferAn;

import java.util.List;

/**
 * Created by Promar on 09.02.2017.
 */
public interface OfferAnService {

    void saveNewOffer(OfferAnDto offerAnDto);

    void updateOffer(OfferAn offerAn);

    OfferAn findByIdOffer(String id);

    List<OfferAn> allWaitingOfferAn();

    List<OfferAn> allConfirmOfferAn();
}
