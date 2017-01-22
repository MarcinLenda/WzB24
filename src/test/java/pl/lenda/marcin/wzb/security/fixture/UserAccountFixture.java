package pl.lenda.marcin.wzb.security.fixture;

import pl.lenda.marcin.wzb.entity.UserAccount;

/**
 * Created by Promar on 16.01.2017.
 */
public class UserAccountFixture {

    public static UserAccount userAccount() {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("mlenda@bimsplus.com.pl");
        userAccount.setNumberUser("3758");
        userAccount.setSurname("Lenda");
        userAccount.setName("Marcin");
        userAccount.setRole("ADMIN");
        userAccount.setPassword("bla");
        userAccount.setId("1234");

        return userAccount;
    }
}
