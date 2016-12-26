package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lenda.marcin.wzb.dto.UserAccountDto;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;
import pl.lenda.marcin.wzb.service.user_account.UserAccountService;

/**
 * Created by Promar on 21.11.2016.
 */
@RestController
public class LoginCtrl {


    //a
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    ConvertTo convertTo;

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(path = "/unauthorized", method = RequestMethod.GET)
    public ResponseEntity<Void> login() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(path = "/success", method = RequestMethod.GET)
    public ResponseEntity<UserAccountDto> success() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount userAccount = userAccountService.findByUsername(authentication.getName());
        UserAccountDto userAccountDto = convertTo.converToUserAccountDto(userAccount);
        return ResponseEntity.status(HttpStatus.OK).body(userAccountDto);
    }
}
