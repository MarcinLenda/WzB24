package pl.lenda.marcin.wzb.security.fixture;

import pl.lenda.marcin.wzb.entity.TraderAccount;
import pl.lenda.marcin.wzb.entity.UserAccount;

/**
 * Created by Promar on 16.01.2017.
 */
public class TraderAccountFixture {

    public static TraderAccount traderAccountBasedOnUserAccount(UserAccount userAccount) {
        TraderAccount traderAccount = new TraderAccount();
        traderAccount.setName(userAccount.getName());
        traderAccount.setNumberTrader(userAccount.getNumberUser());
        traderAccount.setSurname(userAccount.getSurname());
        traderAccount.setNameTeam("STA");
        traderAccount.setId("441122");

        return traderAccount;
    }
}
