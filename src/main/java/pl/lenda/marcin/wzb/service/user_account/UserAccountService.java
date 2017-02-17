package pl.lenda.marcin.wzb.service.user_account;

import pl.lenda.marcin.wzb.dto.ChangePasswordDto;
import pl.lenda.marcin.wzb.entity.UserAccount;

import java.util.List;
import java.util.Optional;

/**
 * Created by Promar on 06.11.2016.
 */
public interface UserAccountService {

    void registerNewUser(UserAccount userAccount);

    Optional<UserAccount> findByUsername(String username);

    UserAccount editData(UserAccount userAccount);

    UserAccount findByNameAndSurname(String name, String surname);

    Optional<UserAccount> findByNumberUser(String numberUser);

    List<UserAccount> findActiveAccount();

    List<UserAccount> findNotActiveAccount();

    List<UserAccount> findAllAccount();

    boolean updateRole(UserAccount userAccount);

    void changePassword(ChangePasswordDto changePasswordDto, UserAccount userAccount);

    void removeAccount(UserAccount userAccount);

    void makeActiveAccount(UserAccount userAccount);

    String getRoleOfLoggedUser();
}
