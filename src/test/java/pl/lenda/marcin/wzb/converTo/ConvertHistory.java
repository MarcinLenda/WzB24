package pl.lenda.marcin.wzb.converTo;

import org.junit.Test;
import pl.lenda.marcin.wzb.converTo.fixture.DocumentWzFixture;
import pl.lenda.marcin.wzb.entity.DocumentWz;
import pl.lenda.marcin.wzb.entity.HistoryCorrectsDocument;
import pl.lenda.marcin.wzb.entity.HistoryDeleteDocumentWz;
import pl.lenda.marcin.wzb.service.client_account.ClientAccountService;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;
import pl.lenda.marcin.wzb.service.trader.TraderService;

import java.util.Date;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by Promar on 25.01.2017.
 */
public class ConvertHistory {

    @Test
    public void convertHistoryDeleteDocumentTest(){
        //given
        DocumentWz documentWz = DocumentWzFixture.documentWzEntity();
        String username = "jankowalski@ims.pl";


        ClientAccountService accountService = mock(ClientAccountService.class);
        TraderService traderService = mock(TraderService.class);


        //when
        ConvertTo convertTo = new ConvertTo(accountService, traderService);
        HistoryDeleteDocumentWz historyDeleteDocumentWz = convertTo.convertToHistoryDeleteDoc(documentWz.getNumberWZ(),
                documentWz.getSubProcess(),documentWz.getClient(),documentWz.getTraderName(),username);

        //then
        validateConvertToHistoryDeleteDocument(documentWz,username,historyDeleteDocumentWz);

    }

    @Test
    public void convertHistoryCorrectDocumentTest() {
        DocumentWz documentWz = DocumentWzFixture.documentWzEntity();
        String username = "karolkowalski@ims.pl";

        ClientAccountService accountService = mock(ClientAccountService.class);
        TraderService traderService = mock(TraderService.class);

        //when
        ConvertTo convertTo = new ConvertTo(accountService, traderService);
        HistoryCorrectsDocument historyCorrectsDocument = convertTo.convertToHistoryCorrectDoc(documentWz.getNumberWZ(),
                documentWz.getSubProcess(),documentWz.getClient(),documentWz.getTraderName(),username);

        //then

        validateConvertToHistoryCorrectDocument(documentWz,username, historyCorrectsDocument);

    }


    private void validateConvertToHistoryDeleteDocument(DocumentWz documentWz, String username,
                                                        HistoryDeleteDocumentWz exceptedHistoryDeleteDocument){

        Date  date = new Date();
        assertThat(documentWz.getNumberWZ()).isEqualTo(exceptedHistoryDeleteDocument.getNumberWZ());
        assertThat(documentWz.getSubProcess()).isEqualTo(exceptedHistoryDeleteDocument.getSubPro());
        assertThat(documentWz.getClient()).isEqualTo(exceptedHistoryDeleteDocument.getNameClient());
        assertThat(documentWz.getTraderName()).isEqualTo(exceptedHistoryDeleteDocument.getNameTrader());
        assertThat(username).isEqualTo(exceptedHistoryDeleteDocument.getUser());
        assertThat(date).isEqualTo(exceptedHistoryDeleteDocument.getDate());
    }

    private void validateConvertToHistoryCorrectDocument(DocumentWz documentWz, String username,
                                                        HistoryCorrectsDocument exceptedHistoryCorrectDocument){

        Date  date = new Date();
        assertThat(documentWz.getNumberWZ()).isEqualTo(exceptedHistoryCorrectDocument.getNumberWZ());
        assertThat(documentWz.getSubProcess()).isEqualTo(exceptedHistoryCorrectDocument.getSubPro());
        assertThat(documentWz.getClient()).isEqualTo(exceptedHistoryCorrectDocument.getNameClient());
        assertThat(documentWz.getTraderName()).isEqualTo(exceptedHistoryCorrectDocument.getNameTrader());
        assertThat(username).isEqualTo(exceptedHistoryCorrectDocument.getUser());
        assertThat(date).isEqualTo(exceptedHistoryCorrectDocument.getDate());
    }
}
