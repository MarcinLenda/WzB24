package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.lenda.marcin.wzb.dto.ItemsReservedFindByDto;
import pl.lenda.marcin.wzb.entity.ItemReservedUnnecessary;
import pl.lenda.marcin.wzb.entity.ItemsReserved;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.service.reserved_items.ItemsReservedUnnecessaryService;
import pl.lenda.marcin.wzb.service.reserved_items.Reserved_ItemsService;
import pl.lenda.marcin.wzb.service.reserved_items.update.UpdateItemsReserved;
import pl.lenda.marcin.wzb.service.upload.UploadFile;
import pl.lenda.marcin.wzb.service.user_account.UserAccountService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Promar on 26.11.2016.
 */
@RestController
@RequestMapping("/rf")
public class ItemsReservedCtrl {

    @Autowired
    Reserved_ItemsService reserved_itemsService;
    @Autowired
    ItemsReservedUnnecessaryService itemsReservedUnnecessaryService;
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    UploadFile uploadFile;
    @Autowired
    UpdateItemsReserved updateItemsReserved;


    @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR"})
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public void handleFileUpload(MultipartFile file) {
        System.out.println("HandleFileUpload");
        uploadFile.uploadFileCsv(file);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR"})
    @RequestMapping(value = "/save_items", method = RequestMethod.GET)
    public void saveItems() {
        updateItemsReserved.uploadItems();

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR", "ROLE_SUPER_USER", "ROLE_USER"})
    @RequestMapping(value = "/update_items", method = RequestMethod.POST)
    public void updateItems(@RequestBody ItemsReservedFindByDto itemsReservedFindByDto) {
        updateItemsReserved.updateItems(itemsReservedFindByDto);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR","ROLE_SUPER_USER", "ROLE_USER"})
    @RequestMapping(value = "/findAll_items", method = RequestMethod.GET)
    public List<ItemsReserved> allItems() {
        return reserved_itemsService.findAll();
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR","ROLE_SUPER_USER", "ROLE_USER"})
    @RequestMapping(value = "/findItemBy_ID", method = RequestMethod.POST)
    public ItemsReserved allItems(@RequestBody ItemsReservedFindByDto _itemsReservedFindByDto) {
        return reserved_itemsService.findItem(_itemsReservedFindByDto.getId());
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR","ROLE_SUPER_USER", "ROLE_USER"})
    @RequestMapping(value = "/findItemBy_nameTrader", method = RequestMethod.GET)
    public List<ItemsReserved> allItemsTrader() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount userAccount = userAccountService.findByUsername(authentication.getName());
        List<ItemsReserved> lista = new ArrayList<>();
        lista = reserved_itemsService.findAllItemsTrader(userAccount.getSurname());
        return lista;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN","ROLE_MODERATOR","ROLE_SUPER_USER", "ROLE_USER"})
    @RequestMapping(value = "/item_change_status", method = RequestMethod.POST)
    public void changeStatusItem(@RequestBody ItemsReservedFindByDto _itemsReservedFindByDto) {
        ItemsReserved _itemsReserved = reserved_itemsService.findItem(_itemsReservedFindByDto.getId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserAccount userAccount = userAccountService.findByUsername(authentication.getName());

        if(_itemsReserved.getCreator().equals(userAccount.getSurname())) {

            ItemReservedUnnecessary itemReservedUnnecessary = new ItemReservedUnnecessary();
            itemReservedUnnecessary.setNumberPro(_itemsReserved.getNumberPro());
            itemReservedUnnecessary.setSubPro(_itemsReserved.getSubPro());
            itemReservedUnnecessary.setPosition(_itemsReserved.getPosition());
            itemsReservedUnnecessaryService.saveItems(itemReservedUnnecessary);
            _itemsReserved.setStatusItem(true);
        }
        reserved_itemsService.saveItems(_itemsReserved);

    }
}
