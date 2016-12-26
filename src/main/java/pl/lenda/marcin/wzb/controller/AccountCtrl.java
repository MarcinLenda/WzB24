package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.lenda.marcin.wzb.dto.*;
import pl.lenda.marcin.wzb.entity.TraderAccount;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;
import pl.lenda.marcin.wzb.service.document_wz.DocumentWzService;
import pl.lenda.marcin.wzb.service.mail.MailService;
import pl.lenda.marcin.wzb.service.trader.TraderService;
import pl.lenda.marcin.wzb.service.user_account.UserAccountService;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Promar on 06.11.2016.
 */
@RestController
@RequestMapping("/myAccount")
public class AccountCtrl {

    @Autowired
    UserAccountService userAccountService;
    @Autowired
    TraderService traderService;
    @Autowired
    DocumentWzService documentWzService;
    @Autowired
    ConvertTo convertTo;
    @Autowired
    MailService mailService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private final Map<String, Object> response = new LinkedHashMap<>();

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/create_account", method = RequestMethod.POST)
    public Map<String, Object> createNewUser(@Valid @RequestBody UserAccountDto userAccountDto,
                                             BindingResult bindingResult) {
        response.clear();
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            response.put("message", "Error");
            for (FieldError error : errors) {
                response.put(error.getObjectName(), error.getDefaultMessage());
            }
        }

        if (userAccountService.findByUsername(userAccountDto.getUsername()) != null) {
            response.put("Error", "ExistsUser");
            return response;
        }

        if(userAccountDto.getNumberUser().startsWith("0")){
            response.put("Error", "Start_with_0");
            return response;
        }

        if (userAccountService.findByNumberUser(userAccountDto.getNumberUser()) != null) {
            response.put("Error", "ExistsNumber");
            return response;
        }

        if (!userAccountDto.getPassword().equals(userAccountDto.getConfirmPassword())) {
            response.put("Error", "WrongPass");
            return response;
        } else {
            userAccountService.registerNewUser(convertTo.converToUserAccountEntity(userAccountDto));
            response.put("Success", "Create");
        }
        return response;
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/find_notactive_account", method = RequestMethod.GET)
    public List<UserAccount> findUserNotActive() {
        return userAccountService.findNotActiveAccount();
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/active_account", method = RequestMethod.GET)
    public List<UserAccountDto> findAllActiveAccount() {
        List<UserAccountDto> listDto = new ArrayList();
        List<UserAccount> listEntity = userAccountService.findActiveAccount();

        for (UserAccount entity : listEntity) {
            UserAccountDto userAccountDto = convertTo.converToUserAccountDto(entity);
            TraderAccount traderAccount = traderService.findByTraderSurnameAndNumber(entity.getSurname(), entity.getNumberUser());
            userAccountDto.setRole(entity.getRole());
            if (traderAccount != null) {
                userAccountDto.setNameTeam(traderAccount.getNameTeam());
            } else {
                userAccountDto.setNameTeam("X");
            }

            listDto.add(userAccountDto);

        }
        return listDto;
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/make_active_account", method = RequestMethod.PATCH)
    public void makeAccountActive(@RequestBody UserAccountActiveOrRemoveDto userAccountActiveOrRemoveDto) throws MessagingException {
        UserAccount userAccount = userAccountService.findByUsername(userAccountActiveOrRemoveDto.getUsername());
        userAccount.setActive(true);
        userAccountService.makeActiveAccount(userAccount);
        mailService.mailConfirmAccount(userAccount.getUsername(), userAccount.getName(), userAccount.getSurname(), userAccount.getUsername(), userAccount.getNumberUser());

    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/block_account", method = RequestMethod.PATCH)
    public void blockAccount(@RequestBody UserAccountActiveOrRemoveDto userAccountActiveOrRemoveDto) {
        UserAccount userAccount = userAccountService.findByUsername(userAccountActiveOrRemoveDto.getUsername());
        userAccount.setActive(false);
        userAccountService.registerNewUser(userAccount);
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/give_admin", method = RequestMethod.POST)
    public boolean giveRoleAdmin(@RequestBody String username) {

        UserAccount userAccount = userAccountService.findByUsername(username);
        userAccount.setRole("ADMIN");
        return userAccountService.updateRole(userAccount);
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/give_user", method = RequestMethod.POST)
    public boolean giveRoleUser(@RequestBody String username) {

        UserAccount userAccount = userAccountService.findByUsername(username);
        userAccount.setRole("USER");
        return userAccountService.updateRole(userAccount);
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public boolean getRole() {
        if (userAccountService.getRoleOfLoggedUser().equals("ROLE_ADMIN")) {
            return true;
        }
        return false;
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/user_info", method = RequestMethod.GET)
    public UserAccountDto userInfo() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount userAccount = userAccountService.findByUsername(authentication.getName());
        TraderAccount traderAccount = traderService.findBySurname(userAccount.getSurname());

        UserAccountDto userAccountDto = new UserAccountDto();
        userAccountDto = convertTo.converToUserAccountDto(userAccount);

        if (traderAccount != null) {
            userAccountDto.setNameTeam(traderAccount.getNameTeam());
        } else {
            userAccountDto.setNameTeam("X");
        }
        return userAccountDto;
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
    public Map<String, Object> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        response.clear();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount userAccount = userAccountService.findByUsername(authentication.getName());

        if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmNewPassword())) {
            response.put("Error", "diffPass");
            return response;
        }

        if (bCryptPasswordEncoder.matches(changePasswordDto.getOldPassword(), userAccount.getPassword()) &&
                changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmNewPassword())) {

            userAccountService.changePassword(changePasswordDto, userAccount);
            response.put("Success", "newPass");
            return response;
        } else {
            response.put("Error", "wrongPass");
            return response;
        }
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public void removeAccount(@RequestBody UserAccountActiveOrRemoveDto userAccountActiveOrRemoveDto){
       UserAccount userAccount = userAccountService.findByUsername(userAccountActiveOrRemoveDto.getUsername());
        userAccountService.removeAccount(userAccount);
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/find_user", method = RequestMethod.POST)
    public UserAccount findUserAccount(@RequestBody FindUserAccountDto findUserAccountDto){
      UserAccount userAccount = userAccountService.findByUsername(findUserAccountDto.getUsername());
        return userAccount;
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/edit_date", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public Map<String, Object> updateUserAccount(@RequestBody UpdateUserAccountDto updateUserAccountDto){
        response.clear();
        UserAccount userAccount = userAccountService.findByUsername(updateUserAccountDto.getUsername());

        if(updateUserAccountDto.getName() != ""){
            userAccount.setName(updateUserAccountDto.getName());
            userAccountService.editData(userAccount);
            response.put("Success", "Zmieniono imie użytkownika.");
            return response;

        }else if(updateUserAccountDto.getSurname() != ""){
            userAccount.setSurname(updateUserAccountDto.getSurname());
            userAccountService.editData(userAccount);
            response.put("Success", "Zmienono nazwisko użytkownika.");
            return response;

        } else if(updateUserAccountDto.getNumberUser() != ""){

            if(userAccountService.findByNumberUser(updateUserAccountDto.getNumberUser()) != null){
                response.put("Error", "Numer istnieje w bazie danych.");
                return response;
            }else {
                userAccount.setNumberUser(updateUserAccountDto.getNumberUser());
                userAccountService.editData(userAccount);
                response.put("Success", "Zmieniono numer użytkownika.");
                return response;
            }
        }else if(updateUserAccountDto.getNewUsername() != null){

            if(userAccountService.findByUsername(updateUserAccountDto.getUsername()) != null){
                response.put("Error", "Podany numer e-mail jest już zajęty.");
                return response;
            }else{
                userAccount.setUsername(updateUserAccountDto.getNewUsername());
                userAccountService.editData(userAccount);
                response.put("Success", "Zmieniono adres e-mail.");
                return response;
            }

        }
        response.put("Error", "Żądanie zakończone niepowodzeniem.");
        return response;
    }
}


