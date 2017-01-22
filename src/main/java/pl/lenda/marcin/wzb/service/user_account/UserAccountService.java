package pl.lenda.marcin.wzb.service.user_account;

import pl.lenda.marcin.wzb.dto.ChangePasswordDto;
import pl.lenda.marcin.wzb.dto.UserAccountActiveOrRemoveDto;
import pl.lenda.marcin.wzb.entity.UserAccount;

import javax.mail.MessagingException;
import java.util.List;

/**
 * Created by Promar on 06.11.2016.
 */
public interface UserAccountService {

    void registerNewUser(UserAccount userAccount);

    UserAccount findByUsername(String username);

    UserAccount editData(UserAccount userAccount);

    UserAccount findByNameAndSurname(String name, String surname);

    UserAccount findByNumberUser(String numberUser);

    List<UserAccount> findActiveAccount();

    List<UserAccount> findNotActiveAccount();

    List<UserAccount> findAllAccount();

    boolean updateRole(UserAccount userAccount);

    void changePassword(ChangePasswordDto changePasswordDto, UserAccount userAccount);

    void removeAccount(UserAccount userAccount);

    void makeActiveAccount(UserAccountActiveOrRemoveDto userAccount) throws MessagingException;

    String getRoleOfLoggedUser();
}
