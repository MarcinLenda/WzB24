package pl.lenda.marcin.wzb.service.user;

import pl.lenda.marcin.wzb.entity.UserAccount;

import java.util.List;

/**
 * Created by Promar on 28.10.2016.
 */
public interface UserService {

    UserAccount registerUser(UserAccount userAccount);

    UserAccount updateAccount(UserAccount userAccount);

    void removeAccount(UserAccount userAccount);

    List<UserAccount> findAllUser();
}
