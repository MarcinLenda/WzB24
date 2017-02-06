package pl.lenda.marcin.wzb.converTo;

import org.junit.Test;
import pl.lenda.marcin.wzb.converTo.fixture.ClientAccountFixture;
import pl.lenda.marcin.wzb.dto.ClientAccountDto;
import pl.lenda.marcin.wzb.entity.ClientAccount;
import pl.lenda.marcin.wzb.service.client_account.ClientAccountService;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;
import pl.lenda.marcin.wzb.service.trader.TraderService;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by Promar on 24.01.2017.
 */
public class ConvertClientAccount {


    @Test
    public void convertClientAccountDtoToEntityTest() {
        ClientAccountDto clientAccountDto = ClientAccountFixture.clientAccountDto();
        ClientAccountService accountService = mock(ClientAccountService.class);
        TraderService traderService = mock(TraderService.class);

        //when
        ConvertTo convertTo = new ConvertTo(accountService, traderService);
        ClientAccount clientAccount = convertTo.convertClientAccountDtoToEntity(clientAccountDto);

        //then
        validateConvertClientDtoToEntity(clientAccountDto, clientAccount);
    }

    @Test
    public void convertClientAccountEntityToDtoTest() {
        ClientAccount clientAccount = ClientAccountFixture.clientAccount();
        ClientAccountService accountService = mock(ClientAccountService.class);
        TraderService traderService = mock(TraderService.class);

        //when
        ConvertTo convertTo = new ConvertTo(accountService, traderService);
        ClientAccountDto clientAccountDto = convertTo.convertClientEntityToDto(clientAccount);

        //then
        validateConvertClientEntityToDto(clientAccount, clientAccountDto);

    }

    private void validateConvertClientEntityToDto(ClientAccount actualEntity, ClientAccountDto expectedClientAccountDto) {

        assertThat(actualEntity.getNumberClient()).isEqualTo(expectedClientAccountDto.getNumberClient());
        assertThat(actualEntity.getName()).isEqualTo(expectedClientAccountDto.getName());
        assertThat(actualEntity.getNameTeam()).isEqualTo(expectedClientAccountDto.getNameTeam());
        assertThat(actualEntity.getAbbreviationName()).isEqualTo(expectedClientAccountDto.getAbbreviationName());
    }

    private void validateConvertClientDtoToEntity(ClientAccountDto actualDto, ClientAccount expectedClientAccount) {

        assertThat(actualDto.getAbbreviationName()).isEqualTo(expectedClientAccount.getAbbreviationName());
        assertThat(actualDto.getName()).isEqualTo(expectedClientAccount.getName());
        assertThat(actualDto.getNameTeam()).isEqualTo(expectedClientAccount.getNameTeam());
        assertThat(actualDto.getNumberClient()).isEqualTo(expectedClientAccount.getNumberClient());
    }

}
