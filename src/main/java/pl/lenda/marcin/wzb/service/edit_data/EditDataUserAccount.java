package pl.lenda.marcin.wzb.service.edit_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.lenda.marcin.wzb.dto.UpdateUserAccountDto;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.service.user_account.UserAccountService;
import pl.lenda.marcin.wzb.service.validate.ValidateUserAccount;

/**
 * Created by Promar on 11.01.2017.
 */
@Component
public class EditDataUserAccount {

    @Autowired
    UserAccountService userAccountService;
    @Autowired
    ValidateUserAccount validateUserAccount;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void changeDataUserAccount(UpdateUserAccountDto updateUserAccountDto, UserAccount userAccount) {

        userAccountService.editData(validateUserAccount.userAccountCheckData(updateUserAccountDto, userAccount));
    }

    public void resetPassword(UpdateUserAccountDto updateUserAccountDto){
        UserAccount userAccount = userAccountService.findByUsername(updateUserAccountDto.getUsername());
        userAccount.setPassword(bCryptPasswordEncoder.encode(userAccount.getUsername()));
        userAccountService.editData(userAccount);
    }

}

