package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.lenda.marcin.wzb.dto.OfferAnDto;
import pl.lenda.marcin.wzb.entity.OfferAn;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;
import pl.lenda.marcin.wzb.service.offer_an.OfferAnService;
import pl.lenda.marcin.wzb.service.user_account.UserAccountService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by Promar on 09.02.2017.
 */
@RestController
@RequestMapping("/an")
public class OfferAnCtrl {

    @Autowired
    private OfferAnService offerAnService;
    @Autowired
    private ConvertTo convertTo;
    @Autowired
    private UserAccountService userAccountService;

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR","ROLE_SUPER_USER","ROLE_USER"})
    @RequestMapping(value = "/save_an", method = RequestMethod.POST)
    @ResponseBody
    public void saveClient(@RequestBody @Valid OfferAnDto offerAnDto) {
        offerAnDto.setWaitingAN(true);
        offerAnService.saveNewOffer(offerAnDto);
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR","ROLE_SUPER_USER","ROLE_USER"})
    @RequestMapping(value = "/confirm_an", method = RequestMethod.POST)
    @ResponseBody
    public void confirmAn(@RequestBody @Valid OfferAnDto offerAnDto) {
        OfferAn offerAn = offerAnService.findByIdOffer(offerAnDto.getId());
        offerAn.setWhoCreate(offerAnDto.getWhoCreate());
        offerAn.setWaitingAN(false);
        offerAn.setStatus("przyjęte");
        offerAnService.updateOffer(offerAn);
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR","ROLE_SUPER_USER","ROLE_USER"})
    @RequestMapping(value = "/change_status_an", method = RequestMethod.POST)
    @ResponseBody
    public void changeStatus(@RequestBody @Valid OfferAnDto offerAnDto) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserAccount> userAccount = userAccountService.findByUsername(authentication.getName());

        offerAnService.updateOffer(convertTo.convertToOfferAnEntity(offerAnDto,userAccount.get()));
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR","ROLE_SUPER_USER","ROLE_USER"})
    @RequestMapping(value = "/all_waiting_an", method = RequestMethod.GET)
    @ResponseBody
    public List<OfferAn> allWaitingOfferAn() {

        return offerAnService.allWaitingOfferAn();
    }


    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR","ROLE_SUPER_USER","ROLE_USER"})
    @RequestMapping(value = "/all_confirm_an", method = RequestMethod.GET)
    @ResponseBody
    public List<OfferAn> allConfirmOffer() {
        return offerAnService.allConfirmOfferAn();
    }
}
