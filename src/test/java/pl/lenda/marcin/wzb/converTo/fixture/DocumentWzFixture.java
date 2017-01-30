package pl.lenda.marcin.wzb.converTo.fixture;

import pl.lenda.marcin.wzb.dto.DocumentWzDto;
import pl.lenda.marcin.wzb.entity.DocumentWz;

import java.util.Date;

/**
 * Created by Promar on 23.01.2017.
 */
public class DocumentWzFixture {

    public static DocumentWz documentWzEntity(){

        Date date = new Date();
        DocumentWz documentWz = new DocumentWz();
        documentWz.setNumberWZ("14241213");
        documentWz.setSubProcess("1");
        documentWz.setClient("Santech Lesniewski S.C.");
        documentWz.setDate(date);
        documentWz.setClientNumber("223151");
        documentWz.setNameTeam("STA");
        documentWz.setTraderName("Hospodiuk");
        documentWz.setBeCorrects(true);
        documentWz.setAbbreviationName("Santech");
        return documentWz;
    }

    public static DocumentWzDto  documentWzDto(){

        Date date = new Date();
        DocumentWzDto documentWzDto = new DocumentWzDto();
        documentWzDto.setNameTeam("STB");
        documentWzDto.setAbbreviationName("Hydronics");
        documentWzDto.setBeCorrects(false);
        documentWzDto.setClient("Hydronics");
        documentWzDto.setClientNumber("331121");
        documentWzDto.setDate(date);
        documentWzDto.setNumberWZ("14221351");
        documentWzDto.setTraderName("Kliber");
        return documentWzDto;
    }
}
