package pl.lenda.marcin.wzb.converTo;

import org.junit.Test;
import pl.lenda.marcin.wzb.converTo.fixture.TraderAccountFixture;
import pl.lenda.marcin.wzb.dto.TraderAccountDto;
import pl.lenda.marcin.wzb.entity.TraderAccount;
import pl.lenda.marcin.wzb.service.client_account.ClientAccountService;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;
import pl.lenda.marcin.wzb.service.trader.TraderService;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by Promar on 25.01.2017.
 */
public class ConvertTrader {

    @Test
    public void convertTraderToEntityTest(){
        TraderAccountDto traderAccountDto = TraderAccountFixture.traderAccountDto();

        ClientAccountService accountService = mock(ClientAccountService.class);
        TraderService traderService = mock(TraderService.class);

        //when
        ConvertTo convertTo = new ConvertTo(accountService, traderService);
        TraderAccount traderAccount = convertTo.convertToTraderEntity(traderAccountDto);

        //then
        validateConvertToTraderEntity(traderAccountDto, traderAccount);



    }

    @Test
    public void convertTraderToDtoTest(){
        TraderAccount traderAccount = TraderAccountFixture.traderAccount();

        ClientAccountService accountService = mock(ClientAccountService.class);
        TraderService traderService = mock(TraderService.class);

        //when
        ConvertTo convertTo = new ConvertTo(accountService, traderService);
        TraderAccountDto traderAccountDto = convertTo.convertToTraderDto(traderAccount);

        //then
        validateConvertToTraderDto(traderAccount, traderAccountDto);

    }

    private void validateConvertToTraderEntity(TraderAccountDto actualTraderDto,
                                               TraderAccount traderAccountExpected){

        assertThat(actualTraderDto.getName()).isEqualTo(traderAccountExpected.getName());
        assertThat(actualTraderDto.getSurname()).isEqualTo(traderAccountExpected.getSurname());
        assertThat(actualTraderDto.getNameTeam()).isEqualTo(traderAccountExpected.getNameTeam());
        assertThat(actualTraderDto.getNumberTrader()).isEqualTo(traderAccountExpected.getNumberTrader());

    }

    private void validateConvertToTraderDto(TraderAccount actualTraderEntity,
                                            TraderAccountDto traderDtoExpected){

        assertThat(actualTraderEntity.getNumberTrader()).isEqualTo(traderDtoExpected.getNumberTrader());
        assertThat(actualTraderEntity.getNameTeam()).isEqualTo(traderDtoExpected.getNameTeam());
        assertThat(actualTraderEntity.getName()).isEqualTo(traderDtoExpected.getName());
        assertThat(actualTraderEntity.getSurname()).isEqualTo(traderDtoExpected.getSurname());
    }
}
