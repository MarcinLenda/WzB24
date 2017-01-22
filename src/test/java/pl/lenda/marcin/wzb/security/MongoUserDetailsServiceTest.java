package pl.lenda.marcin.wzb.security;

import org.junit.Test;
import pl.lenda.marcin.wzb.dto.UserAccountDto;
import pl.lenda.marcin.wzb.entity.TraderAccount;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.security.fixture.TraderAccountFixture;
import pl.lenda.marcin.wzb.security.fixture.UserAccountFixture;
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
public class MongoUserDetailsServiceTest {


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
        validateUserAccountDto(dto, userAccount, traderAccount.getNameTeam());
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
        validateUserAccountDto(dto, userAccount, "X");
    }

    private void validateUserAccountDto(UserAccountDto actualDto, UserAccount expectedUserAccount, String teamName) {
        assertThat(actualDto.getName()).isEqualTo(expectedUserAccount.getName());
        assertThat(actualDto.getPassword()).isNull();
        assertThat(actualDto.getRole()).isEqualTo(expectedUserAccount.getRole());
        assertThat(actualDto.getUsername()).isEqualTo(expectedUserAccount.getUsername());
        assertThat(actualDto.getNumberUser()).isEqualTo(expectedUserAccount.getNumberUser());
        assertThat(actualDto.getSurname()).isEqualTo(expectedUserAccount.getSurname());
        assertThat(actualDto.getNameTeam()).isEqualTo(teamName);
    }

}
