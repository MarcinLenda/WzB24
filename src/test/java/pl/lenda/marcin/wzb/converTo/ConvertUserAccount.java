package pl.lenda.marcin.wzb.converTo;

import org.junit.Test;
import pl.lenda.marcin.wzb.dto.UserAccountDto;
import pl.lenda.marcin.wzb.entity.TraderAccount;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.converTo.fixture.TraderAccountFixture;
import pl.lenda.marcin.wzb.converTo.fixture.UserAccountFixture;
import pl.lenda.marcin.wzb.service.client_account.ClientAccountService;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;
import pl.lenda.marcin.wzb.service.trader.TraderService;

import java.util.Optional;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Promar on 16.01.2017.
 */
public class ConvertUserAccount {


    @Test
    public void convertToUserAccountDtoTest(){
        //given
        UserAccount userAccount = UserAccountFixture.userAccount();
        TraderAccount traderAccount = TraderAccountFixture.traderAccountBasedOnUserAccount(userAccount);

        ClientAccountService accountService = mock(ClientAccountService.class);
        TraderService traderService = mock(TraderService.class);

        when(traderService.findByTraderSurnameAndNumber(eq(userAccount.getSurname()), eq(userAccount.getNumberUser())))
            .thenReturn(Optional.of(traderAccount));

        //when
        ConvertTo convertTo = new ConvertTo(accountService, traderService);
        UserAccountDto dto = convertTo.convertToUserAccountDto(userAccount);

        //then
        validateConvertToUserAccountDto(dto, userAccount, traderAccount.getNameTeam());
    }

    @Test
    public void convertToUserAccountDtoCheckNameTeamTest() {
        //given
        UserAccount userAccount = UserAccountFixture.userAccount();

        ClientAccountService accountService = mock(ClientAccountService.class);
        TraderService traderService = mock(TraderService.class);

        when(traderService.findByTraderSurnameAndNumber(eq(userAccount.getSurname()), eq(userAccount.getNumberUser())))
                .thenReturn(Optional.empty());

        //when
        ConvertTo convertTo = new ConvertTo(accountService, traderService);
        UserAccountDto dto = convertTo.convertToUserAccountDto(userAccount);

        //then
        validateConvertToUserAccountDto(dto, userAccount, "X");
    }

    @Test
    public void convertToUserAccountEntityTest(){
        //given
        UserAccountDto userAccountDto = UserAccountFixture.userAccountDto();

        ClientAccountService accountService = mock(ClientAccountService.class);
        TraderService traderService = mock(TraderService.class);

        //when
        ConvertTo convertTo = new ConvertTo(accountService, traderService);
        UserAccount userAccount = convertTo.convertToUserAccountEntity(userAccountDto);

        //then
        validateConvertToUserAccount(userAccount, userAccountDto);

    }

    private void validateConvertToUserAccountDto(UserAccountDto expectedUserAccountDto, UserAccount actualUserAccount, String teamName) {
        assertThat(expectedUserAccountDto.getName()).isEqualTo(actualUserAccount.getName());
        assertThat(expectedUserAccountDto.getPassword()).isNull();
        assertThat(expectedUserAccountDto.getRole()).isEqualTo(actualUserAccount.getRole());
        assertThat(expectedUserAccountDto.getUsername()).isEqualTo(actualUserAccount.getUsername());
        assertThat(expectedUserAccountDto.getNumberUser()).isEqualTo(actualUserAccount.getNumberUser());
        assertThat(expectedUserAccountDto.getSurname()).isEqualTo(actualUserAccount.getSurname());
        assertThat(expectedUserAccountDto.getNameTeam()).isEqualTo(teamName);
    }

    public void validateConvertToUserAccount(UserAccount expectedUserAccount, UserAccountDto actualUserAccountDto){
        assertThat(expectedUserAccount.getName()).isEqualTo(actualUserAccountDto.getName());
        assertThat(expectedUserAccount.getSurname()).isEqualTo(actualUserAccountDto.getSurname());
        assertThat(expectedUserAccount.getUsername()).isEqualTo(actualUserAccountDto.getUsername());
        assertThat(expectedUserAccount.getPassword()).isEqualTo(actualUserAccountDto.getPassword());
        assertThat(expectedUserAccount.getNumberUser()).isEqualTo(actualUserAccountDto.getNumberUser());

    }

}
