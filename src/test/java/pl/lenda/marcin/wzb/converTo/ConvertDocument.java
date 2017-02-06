package pl.lenda.marcin.wzb.converTo;

import org.junit.Test;
import pl.lenda.marcin.wzb.converTo.fixture.ClientAccountFixture;
import pl.lenda.marcin.wzb.converTo.fixture.DocumentWzFixture;
import pl.lenda.marcin.wzb.converTo.fixture.TraderAccountFixture;
import pl.lenda.marcin.wzb.dto.DocumentWzDto;
import pl.lenda.marcin.wzb.entity.ClientAccount;
import pl.lenda.marcin.wzb.entity.DocumentWz;
import pl.lenda.marcin.wzb.entity.TraderAccount;
import pl.lenda.marcin.wzb.service.client_account.ClientAccountService;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;
import pl.lenda.marcin.wzb.service.trader.TraderService;

import java.util.Optional;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Promar on 23.01.2017.
 */
public class ConvertDocument {


    @Test
    public void convertDocumentToDtoTest(){
        DocumentWz documentWz = DocumentWzFixture.documentWzEntity();

        ClientAccountService accountService = mock(ClientAccountService.class);
        TraderService traderService = mock(TraderService.class);

        //when
        ConvertTo convertTo = new ConvertTo(accountService, traderService);
        DocumentWzDto documentWzDto = convertTo.convertDocumentToDto(documentWz);

        //then
        validateConvertDocumentToDto(documentWzDto, documentWz);
    }

    @Test
    public void convertDocumentToEntityTest(){
        DocumentWzDto documentWzDto = DocumentWzFixture.documentWzDto();
        ClientAccount clientAccount = ClientAccountFixture.clientAccount();
        TraderAccount traderAccount = TraderAccountFixture.traderAccount();

        ClientAccountService clientAccountService = mock(ClientAccountService.class);
        TraderService traderService = mock(TraderService.class);

        when(clientAccountService.findByAbbreviationName(eq(documentWzDto.getClient())))
                .thenReturn(Optional.of(clientAccount));

        when(traderService.findBySurname(eq(documentWzDto.getTraderName())))
                .thenReturn(Optional.of(traderAccount));


        //when
        ConvertTo convertTo = new ConvertTo(clientAccountService, traderService);
        DocumentWz documentWz = convertTo.convertDocumentToEntity(documentWzDto);

        //then
        validateConvertDocumentToEntity(documentWzDto, documentWz, clientAccount.getAbbreviationName(), clientAccount.getNumberClient(),
                traderAccount.getNameTeam(), clientAccount.getName());
    }

    private void validateConvertDocumentToDto(DocumentWzDto documentWzDto, DocumentWz documentWz) {

        assertThat(documentWzDto.getNameTeam()).isEqualTo(documentWz.getNameTeam());
        assertThat(documentWzDto.getClient()).isEqualTo(documentWz.getClient());
        assertThat(documentWzDto.getNumberWZ()).isEqualTo(documentWz.getNumberWZ());
        assertThat(documentWzDto.getSubProcess()).isEqualTo(documentWz.getSubProcess());
        assertThat(documentWzDto.getDate()).isEqualTo(documentWz.getDate());
        assertThat(documentWzDto.getTraderName()).isEqualTo(documentWz.getTraderName());
        assertThat(documentWzDto.getAbbreviationName()).isEqualTo(documentWz.getAbbreviationName());
    }

    private void validateConvertDocumentToEntity(DocumentWzDto documentWzDto, DocumentWz documentWz,
                                                 String abbreviationName, String numberClient, String nameTeam,
                                                 String nameClient){

        assertThat(abbreviationName).isEqualTo(documentWz.getAbbreviationName());
        assertThat(numberClient).isEqualTo(documentWz.getClientNumber());
        assertThat(nameTeam).isEqualTo(documentWz.getNameTeam());
        assertThat(nameClient).isEqualTo(documentWz.getClient());
        assertThat(documentWzDto.getDate()).isEqualTo(documentWz.getDate());
        assertThat(documentWzDto.getNumberWZ()).isEqualTo(documentWz.getNumberWZ());
        assertThat(documentWzDto.getSubProcess()).isEqualTo(documentWz.getSubProcess());

    }

}
