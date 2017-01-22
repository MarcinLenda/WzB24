package pl.lenda.marcin.wzb.service.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.lenda.marcin.wzb.dto.ChangePasswordDto;
import pl.lenda.marcin.wzb.dto.UpdateUserAccountDto;
import pl.lenda.marcin.wzb.dto.UserAccountDto;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.exception.AccountException;
import pl.lenda.marcin.wzb.service.user_account.UserAccountService;

/**
 * Created by Promar on 09.01.2017.
 */
@Component
public class ValidateUserAccount {

    @Autowired
    UserAccountService userAccountService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void userAccountValidate(UserAccountDto userAccountDto) {

        if (userAccountService.findByUsername(userAccountDto.getUsername()) != null) {
            throw AccountException.usernameAlreadyExists();
        }

        if (userAccountService.findByNumberUser(userAccountDto.getNumberUser()) != null) {
            throw AccountException.numberEmployeeAlreadyExists();
        }

    }

    public void userAccountChangePassword(ChangePasswordDto changePasswordDto, UserAccount userAccount) {

        if (bCryptPasswordEncoder.matches(changePasswordDto.getOldPassword(), userAccount.getPassword())) {
            userAccountService.changePassword(changePasswordDto, userAccount);
        } else {
            throw AccountException.wrongPassword();
        }
    }

    public UserAccount userAccountCheckData(UpdateUserAccountDto updateUserAccountDto, UserAccount userAccount) {

        if (!userAccount.getNumberUser().equals(updateUserAccountDto.getNumberUser())) {

            if (userAccountService.findByNumberUser(updateUserAccountDto.getNumberUser()) != null) {
                throw AccountException.numberEmployeeAlreadyExists();
            }
        }

        if (updateUserAccountDto.getNumberUser().charAt(0) == '0') {
            userAccount.setNumberUser(updateUserAccountDto.getNumberUser().
                    substring(1, updateUserAccountDto.getNumberUser().length()));
        } else {
            userAccount.setNumberUser(updateUserAccountDto.getNumberUser());
        }

        userAccount.setSurname(updateUserAccountDto.getSurname());
        userAccount.setName(updateUserAccountDto.getName());
        userAccount.setUsername(updateUserAccountDto.getUsername());

        return userAccount;
    }
}
