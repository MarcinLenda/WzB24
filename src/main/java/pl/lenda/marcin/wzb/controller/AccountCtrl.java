package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.lenda.marcin.wzb.dto.*;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;
import pl.lenda.marcin.wzb.service.document_wz.DocumentWzService;
import pl.lenda.marcin.wzb.service.edit_data.EditDataUserAccount;
import pl.lenda.marcin.wzb.service.trader.TraderService;
import pl.lenda.marcin.wzb.service.user_account.UserAccountService;
import pl.lenda.marcin.wzb.service.validate.ValidateUserAccount;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    ValidateUserAccount validateUserAccount;
    @Autowired
    EditDataUserAccount editDataUserAccount;



    private final Map<String, Object> response = new LinkedHashMap<>();

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/create_account", method = RequestMethod.POST)
    public void createNewUser(@Valid @RequestBody UserAccountDto userAccountDto) {
        validateUserAccount.userAccountValidate(userAccountDto);
        userAccountService.registerNewUser(convertTo.converToUserAccountEntity(userAccountDto));
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/find_notactive_account", method = RequestMethod.GET)
    public List<UserAccountDto> findUserNotActive() {
        return userAccountService.findNotActiveAccount().stream()
                .map(entity -> convertTo.convertToUserAccountDto(entity))
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/active_account", method = RequestMethod.GET)
    public List<UserAccountDto> findAllActiveAccount() {
        return userAccountService.findActiveAccount().stream()
                .map(entity -> convertTo.convertToUserAccountDto(entity))
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/make_active_account", method = RequestMethod.PATCH)
    public void makeAccountActive(@RequestBody UserAccountActiveOrRemoveDto userAccountActiveOrRemoveDto) throws MessagingException {
        userAccountService.makeActiveAccount(userAccountActiveOrRemoveDto);
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
        UserAccountDto userAccountDto = convertTo.convertToUserAccountDto(userAccount);

        return userAccountDto;
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
    public void changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount userAccount = userAccountService.findByUsername(authentication.getName());
        validateUserAccount.userAccountChangePassword(changePasswordDto, userAccount);
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
    public void resetPassword(@RequestBody UpdateUserAccountDto updateUserAccountDto) {
        editDataUserAccount.resetPassword(updateUserAccountDto);
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public void removeAccount(@RequestBody UserAccountActiveOrRemoveDto userAccountActiveOrRemoveDto) {
        UserAccount userAccount = userAccountService.findByUsername(userAccountActiveOrRemoveDto.getUsername());
        userAccountService.removeAccount(userAccount);
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/find_user", method = RequestMethod.POST)
    public UserAccount findUserAccount(@RequestBody FindUserAccountDto findUserAccountDto) {
        UserAccount userAccount = userAccountService.findByUsername(findUserAccountDto.getUsername());
        return userAccount;
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/edit_date", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public void updateUserAccount(@RequestBody @Valid UpdateUserAccountDto updateUserAccountDto) {
        UserAccount userAccount = userAccountService.findByUsername(updateUserAccountDto.getUsername());
        editDataUserAccount.changeDataUserAccount(updateUserAccountDto, userAccount);
    }

}


