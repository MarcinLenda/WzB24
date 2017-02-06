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
import pl.lenda.marcin.wzb.service.mail.MailService;
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
    @Autowired
    MailService mailService;



    private final Map<String, Object> response = new LinkedHashMap<>();

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/create_account", method = RequestMethod.POST)
    public void createNewUser(@Valid @RequestBody UserAccountDto userAccountDto) {
        validateUserAccount.userAccountValidate(userAccountDto);
        userAccountService.registerNewUser(convertTo.convertToUserAccountEntity(userAccountDto));
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN", "ROLE_MODERATOR"})
    @RequestMapping(value = "/find_notactive_account", method = RequestMethod.GET)
    public List<UserAccountDto> findUserNotActive() {
        return userAccountService.findNotActiveAccount().stream()
                .map(entity -> convertTo.convertToUserAccountDto(entity))
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR" })
    @RequestMapping(value = "/active_account", method = RequestMethod.GET)
    public List<UserAccountDto> findAllActiveAccount() {
        return userAccountService.findActiveAccount().stream()
                .map(entity -> convertTo.convertToUserAccountDto(entity))
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    @RequestMapping(value = "/make_active_account", method = RequestMethod.PATCH)
    public void makeAccountActive(@RequestBody UserAccountActiveOrRemoveDto userAccountActiveOrRemoveDto) throws MessagingException {
        UserAccount userAccount = userAccountService.findByUsername(userAccountActiveOrRemoveDto.getUsername());
        userAccount.setActive(true);
        userAccountService.makeActiveAccount(userAccount);
        mailService.mailConfirmAccount(userAccount.getUsername(), userAccount.getName(), userAccount.getSurname(), userAccount.getUsername(), userAccount.getNumberUser());

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    @RequestMapping(value = "/block_account", method = RequestMethod.PATCH)
    public void blockAccount(@RequestBody UserAccountActiveOrRemoveDto userAccountActiveOrRemoveDto) {
        UserAccount userAccount = userAccountService.findByUsername(userAccountActiveOrRemoveDto.getUsername());
        userAccount.setActive(false);
        userAccountService.registerNewUser(userAccount);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @Secured("ROLE_SUPER_ADMIN")
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public boolean giveRoleAdmin(@RequestBody RoleDto roleDto) {
        UserAccount userAccount = userAccountService.findByUsername(roleDto.getUsername());
        userAccount.setRole(roleDto.getRoleLevel());
        return userAccountService.updateRole(userAccount);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR","ROLE_SUPER_USER", "ROLE_USER"})
    @RequestMapping(value = "/user_info", method = RequestMethod.GET)
    public UserAccountDto userInfo() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount userAccount = userAccountService.findByUsername(authentication.getName());
        UserAccountDto userAccountDto = convertTo.convertToUserAccountDto(userAccount);

        return userAccountDto;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR","ROLE_SUPER_USER", "ROLE_USER"})
    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
    public void changePassword(@RequestBody @Valid ChangePasswordDto changePasswordDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount userAccount = userAccountService.findByUsername(authentication.getName());
        validateUserAccount.userAccountChangePassword(changePasswordDto, userAccount);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR"})
    @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
    public void resetPassword(@RequestBody UpdateUserAccountDto updateUserAccountDto) {
        editDataUserAccount.resetPassword(updateUserAccountDto);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @Secured("ROLE_SUPER_ADMIN")
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public void removeAccount(@RequestBody UserAccountActiveOrRemoveDto userAccountActiveOrRemoveDto) {
        UserAccount userAccount = userAccountService.findByUsername(userAccountActiveOrRemoveDto.getUsername());
        userAccountService.removeAccount(userAccount);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR","ROLE_SUPER_USER", "ROLE_USER"})
    @RequestMapping(value = "/find_user", method = RequestMethod.POST)
    public UserAccount findUserAccount(@RequestBody FindUserAccountDto findUserAccountDto) {
        UserAccount userAccount = userAccountService.findByUsername(findUserAccountDto.getUsername());
        return userAccount;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/edit_date", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR"})
    public void updateUserAccount(@RequestBody @Valid UpdateUserAccountDto updateUserAccountDto) {
        UserAccount userAccount = userAccountService.findByUsername(updateUserAccountDto.getUsername());
        editDataUserAccount.changeDataUserAccount(updateUserAccountDto, userAccount);
    }

}


