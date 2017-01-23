package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.lenda.marcin.wzb.dto.ItemsReserverdFindByDto;
import pl.lenda.marcin.wzb.dto.Reserved_ItemsFindStatistics;
import pl.lenda.marcin.wzb.entity.ItemsReserved;
import pl.lenda.marcin.wzb.entity.StatisticsItems;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.service.reserved_items.Reserved_ItemsService;
import pl.lenda.marcin.wzb.service.upload.UploadFile;
import pl.lenda.marcin.wzb.service.user_account.UserAccountService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Promar on 26.11.2016.
 */
@RestController
public class ItemsReservedCtrl {

    @Autowired
    Reserved_ItemsService reserved_itemsService;
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    UploadFile uploadFile;
    @Autowired
    MongoTemplate mongoTemplate;

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody
    void handleFileUpload(MultipartFile file){
        System.out.println("HandleFileUpload");
        uploadFile.uploadPhoto(file);

    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save_items", method = RequestMethod.GET)
    public void saveItems() {

        mongoTemplate.dropCollection("reserved_Items");
        mongoTemplate.dropCollection("itemsReserved");

        String csvFile = "/home/ubuntu/WzB24/src/main/resources/static/rf_raport.csv";
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

                if (items[3].equals("")) {
                    items[3] = "Brak";
                }



                if (items[4].equals("")) {
                    items[4] = "Brak";
                }



                if (items[6].equals("")) {
                    items[6] = "Brak";
                }



                if (items[8].equals("")) {
                    items[8] = "Brak";
                }



                if (items[11].equals("")) {
                    items[11] = "Brak";
                }





                if (items[15].equals("")) {
                    items[15] = "Brak";
                }





                if (items[16].equals("")) {
                    items[16] = "Brak";
                }



                if (items[17].equals("")) {
                    items[17] = "Brak";
                }




                if (items[12].equals("")) {
                    items[12] = "Brak";
                }




                if (items[13].equals("")) {
                    items[13] = "Brak";
                }



                if (items[5].equals("")) {
                    items[5] = "Brak";
                }
                System.out.println(items[10]);

                ItemsReserved _itemsReserved = new ItemsReserved();
                //search name trader
                String numberCreator = "";
                StringBuilder sb = new StringBuilder(items[17]);
                numberCreator = sb.substring(1);

                if(userAccountService.findByNumberUser(numberCreator) != null){
                    UserAccount userAccount = userAccountService.findByNumberUser(numberCreator);
                    _itemsReserved.setCreator(userAccount.getSurname());

                }else{
                    _itemsReserved.setCreator(items[17]);
                }

                _itemsReserved.setKbn(items[3]);
                _itemsReserved.setContentItem(items[4]);
                _itemsReserved.setDetailsContentItem(items[6]);
                _itemsReserved.setNumberFactory(items[5]);
                _itemsReserved.setPieces(items[8]);
                _itemsReserved.setNumberPro(items[12]);
                _itemsReserved.setProvider(items[11]);
                _itemsReserved.setSubPro(items[13]);
                _itemsReserved.setNameTeam(items[15]);
                _itemsReserved.setNameTeamCDS(items[16]);
                _itemsReserved.setStatusItem(false);
                _itemsReserved.setDateUpdate(new Date());

                _itemsReserved.setDateAccepted(new Date());

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

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/update_items", method = RequestMethod.POST)
    public boolean updateItems(@RequestBody ItemsReserverdFindByDto _itemsReserverdFindByDto) {
        ItemsReserved _itemsReserved = reserved_itemsService.findItem(_itemsReserverdFindByDto.getId());
        double piecesSum = Double.parseDouble(_itemsReserved.getPieces()) - Double.parseDouble(_itemsReserverdFindByDto.getPieces());

        _itemsReserved.setPieces(String.valueOf(piecesSum));
        reserved_itemsService.saveItems(_itemsReserved);
        return true;
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/findAll_items", method = RequestMethod.GET)
    public List<ItemsReserved> allItems() {
        return reserved_itemsService.findAll();
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/delete_items", method = RequestMethod.DELETE)
    public void deleteItems(@RequestBody ItemsReserverdFindByDto _itemsReserverdFindByDto) {
        ItemsReserved _itemsReserved = reserved_itemsService.findItem(_itemsReserverdFindByDto.getId());
        reserved_itemsService.delete(_itemsReserved);
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/findItemBy_ID", method = RequestMethod.POST)
    public ItemsReserved allItems(@RequestBody ItemsReserverdFindByDto _itemsReserverdFindByDto) {
        return reserved_itemsService.findItem(_itemsReserverdFindByDto.getId());
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/findItemBy_nameTrader", method = RequestMethod.GET)
    public List<ItemsReserved> allItemsTrader() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount userAccount = userAccountService.findByUsername(authentication.getName());
        List<ItemsReserved> lista = new ArrayList<>();
        lista = reserved_itemsService.findAllItemsTrader(userAccount.getSurname());
        return lista;
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/item_change_status", method = RequestMethod.POST)
    public void changeStatusItem(@RequestBody ItemsReserverdFindByDto _itemsReserverdFindByDto) {
        ItemsReserved _itemsReserved = reserved_itemsService.findItem(_itemsReserverdFindByDto.getId());
        _itemsReserved.setStatusItem(true);
        reserved_itemsService.saveItems(_itemsReserved);

    }


    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/statistics", method = RequestMethod.POST)
    public StatisticsItems TeamStatistics(@RequestBody Reserved_ItemsFindStatistics reserved_itemsFindStatistics) {
        List<ItemsReserved> list_itemReserveds = reserved_itemsService.findByNameTeam(reserved_itemsFindStatistics.getNameTeam());

        Integer last30Days = 0;
        Integer last60Days = 0;
        Integer last90Days = 0;
        Integer last180Days = 0;
        Integer lastYear = 0;
        Integer sum = 0;
        Integer allPieces = 0;

        for (ItemsReserved items : list_itemReserveds) {
            sum = sum + Integer.parseInt(items.getAllPrice());
            allPieces++;
            if (Integer.parseInt(items.getDelay()) < 30) {
                last30Days = last30Days + (Integer.parseInt(items.getPriceItem()) * Integer.parseInt(items.getPieces()));
            }
            if (Integer.parseInt(items.getDelay()) > 30 && Integer.parseInt(items.getDelay()) < 60) {
                last60Days = last60Days + (Integer.parseInt(items.getPriceItem()) * Integer.parseInt(items.getPieces()));

            }
            if (Integer.parseInt(items.getDelay()) > 60 && Integer.parseInt(items.getDelay()) < 90) {
                last90Days = last90Days + (Integer.parseInt(items.getPriceItem()) * Integer.parseInt(items.getPieces()));

            }
            if (Integer.parseInt(items.getDelay()) < 180) {
                last180Days = last90Days + (Integer.parseInt(items.getPriceItem()) * Integer.parseInt(items.getPieces()));

            }
            if( Integer.parseInt(items.getDelay()) < 360){
                lastYear = lastYear + (Integer.parseInt(items.getPriceItem()) * Integer.parseInt(items.getPieces()));
            }
        }

        StatisticsItems statisticsItems = new StatisticsItems();
        statisticsItems.setLast30Days(last30Days);
        statisticsItems.setLast60Days(last60Days);
        statisticsItems.setLast90Days(last90Days);
        statisticsItems.setLast180Days(last180Days);
        statisticsItems.setLastYear(lastYear);
        statisticsItems.setNameTeam(reserved_itemsFindStatistics.getNameTeam());
        statisticsItems.setAllSum(String.valueOf(sum));
        statisticsItems.setAllPieces(String.valueOf(allPieces));
        return statisticsItems;
    }
}
