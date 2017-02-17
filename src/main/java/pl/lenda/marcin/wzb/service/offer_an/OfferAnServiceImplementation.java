package pl.lenda.marcin.wzb.service.offer_an;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.lenda.marcin.wzb.dto.OfferAnDto;
import pl.lenda.marcin.wzb.entity.OfferAn;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.repository.ClientAccountRepository;
import pl.lenda.marcin.wzb.repository.OfferAnRepository;
import pl.lenda.marcin.wzb.repository.TraderAccountRepository;
import pl.lenda.marcin.wzb.repository.UserAccountRepository;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;

import java.util.List;

/**
 * Created by Promar on 09.02.2017.
 */
@Service
public class OfferAnServiceImplementation implements OfferAnService {


    @Autowired
    OfferAnRepository offerAnRepository;
    @Autowired
    TraderAccountRepository traderAccountRepository;
    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    ClientAccountRepository clientAccountRepository;
    @Autowired
    ConvertTo convertTo;


    @Override
    public void saveNewOffer(OfferAnDto offerAnDto) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount userAccount = userAccountRepository.findByUsername(authentication.getName());

        offerAnRepository.save(convertTo.convertToOfferAnEntity(offerAnDto, userAccount));
    }

    @Override
    public void updateOffer(OfferAn offerAn) {
        offerAnRepository.save(offerAn);
    }

    @Override
    public OfferAn findByIdOffer(String id) {
        return offerAnRepository.findById(id);
    }

    @Override
    public List<OfferAn> allWaitingOfferAn() {
        return offerAnRepository.findByWaitingANTrue();
    }

    @Override
    public List<OfferAn> allConfirmOfferAn() {
        return offerAnRepository.findByWaitingANFalse();
    }
}
