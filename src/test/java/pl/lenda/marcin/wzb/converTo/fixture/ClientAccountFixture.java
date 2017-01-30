package pl.lenda.marcin.wzb.converTo.fixture;

import pl.lenda.marcin.wzb.dto.ClientAccountDto;
import pl.lenda.marcin.wzb.entity.ClientAccount;

/**
 * Created by Promar on 23.01.2017.
 */
public class ClientAccountFixture {


    public static ClientAccount clientAccount(){
        ClientAccount clientAccount = new ClientAccount();
        clientAccount.setAbbreviationName("Santech");
        clientAccount.setNumberClient("203040");
        clientAccount.setName("Santech Lesniewski S.C.");
        clientAccount.setNameTeam("STA");
        return clientAccount;
    }

    public static ClientAccountDto clientAccountDto(){
        ClientAccountDto clientAccountDto = new ClientAccountDto();
        clientAccountDto.setAbbreviationName("Medium");
        clientAccountDto.setName("P.U. Medium");
        clientAccountDto.setNameTeam("STE");
        clientAccountDto.setNumberClient("234151");
        return clientAccountDto;
    }
}
