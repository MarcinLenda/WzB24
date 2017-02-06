package pl.lenda.marcin.wzb.converTo.fixture;

import pl.lenda.marcin.wzb.dto.TraderAccountDto;
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

    public static TraderAccount traderAccount(){
        TraderAccount traderAccount = new TraderAccount();
        traderAccount.setNameTeam("STA");
        traderAccount.setName("Marcin");
        traderAccount.setSurname("Pruszanow");
        traderAccount.setNumberTrader("3759");
        return traderAccount;
    }

    public static TraderAccountDto traderAccountDto(){
        TraderAccountDto traderAccountDto = new TraderAccountDto();
        traderAccountDto.setNameTeam("STB");
        traderAccountDto.setName("Karol");
        traderAccountDto.setSurname("Kowalski");
        traderAccountDto.setNumberTrader("3761");
        return traderAccountDto;
    }
}
