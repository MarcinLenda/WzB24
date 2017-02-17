package pl.lenda.marcin.wzb.service.reserved_items.update;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.lenda.marcin.wzb.dto.ItemsReservedFindByDto;
import pl.lenda.marcin.wzb.entity.ItemsReserved;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.service.reserved_items.ItemsReservedUnnecessaryService;
import pl.lenda.marcin.wzb.service.reserved_items.Reserved_ItemsService;
import pl.lenda.marcin.wzb.service.user_account.UserAccountService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Promar on 30.01.2017.
 */
@Component
public class UpdateItemsReserved {

    private static final Logger logger = LoggerFactory.getLogger(UpdateItemsReserved.class);

    @Autowired
    Reserved_ItemsService reserved_itemsService;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    ItemsReservedUnnecessaryService itemsReservedUnnecessaryService;
    @Value("${path.to.read.file.windows}")
    String path;

    public void uploadItems() {
        mongoTemplate.dropCollection("reserved_Items");
        mongoTemplate.dropCollection("itemsReserved");

        String csvFile = "/home/promar/java/WzB24/src/main/resources/static/rf_raport.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        boolean firstLine = true;

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                // use comma as separator
                String[] items = line.split(cvsSplitBy);

                for (int i = 0; i < items.length; i++) {
                    if (items[i].equals("")) {
                        items[i] = "brak";
                    }
                }

                ItemsReserved _itemsReserved = new ItemsReserved();
                //search name trader
                String numberCreator = "";
                StringBuilder sb = new StringBuilder(items[18]);
                numberCreator = sb.substring(1);

                UserAccount userAccount = userAccountService.findByNumberUser(numberCreator);


                if (userAccount != null) {

                    _itemsReserved.setCreator(userAccount.getSurname());

                } else {
                    _itemsReserved.setCreator(items[18]);
                }

                _itemsReserved.setKbn(items[3]);
                _itemsReserved.setContentItem(items[4]);
                _itemsReserved.setDetailsContentItem(items[6]);
                _itemsReserved.setNumberFactory(items[5]);
                _itemsReserved.setPieces(items[8]);
                _itemsReserved.setNumberPro(items[13]);
                _itemsReserved.setProvider(items[12]);
                _itemsReserved.setSubPro(items[14]);
                _itemsReserved.setNameTeam(items[16]);
                _itemsReserved.setPosition(items[15]);
                _itemsReserved.setNameTeamCDS(items[17]);
                _itemsReserved.setSection(items[11]);
                _itemsReserved.setStatusItem(false);
                _itemsReserved.setDateUpdate(new Date());
                _itemsReserved.setDateAccepted(convertTime(items[10].substring(1, items[10].length())));

                //check item or is itemUnecessary
                if (itemsReservedUnnecessaryService.findItemUnnecessary(_itemsReserved.getNumberPro(),
                        _itemsReserved.getSubPro(), _itemsReserved.getPosition()) != null) {
                    _itemsReserved.setStatusItem(true);
                }

                reserved_itemsService.saveItems(_itemsReserved);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void updateItems(ItemsReservedFindByDto itemsReservedFindByDto) {

        ItemsReserved itemsReserved = reserved_itemsService.findItem(itemsReservedFindByDto.getId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        itemsReserved.setActiveItemForTrader(authentication.getName());
        reserved_itemsService.saveItems(itemsReserved);
    }


    private String convertTime(String time) {

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyMMdd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yy");
        String date = "";

        try {
            date = outputFormat.format(inputFormat.parse(time));
            logger.info("Date: {}", date);
        } catch (ParseException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return date;
    }
}

