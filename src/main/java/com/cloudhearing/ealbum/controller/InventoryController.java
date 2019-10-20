package com.cloudhearing.ealbum.controller;

import com.cloudhearing.ealbum.entity.Inventory;
import com.cloudhearing.ealbum.service.InventoryService;
import com.cloudhearing.ealbum.utils.JsonMsg;
import com.cloudhearing.ealbum.utils.StringTool;
import com.cloudhearing.ealbum.utils.TokenTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InventoryController extends BaseController {
    @Autowired
    InventoryService inventoryService;

    @GetMapping("/inventories/")
    public JsonMsg listAll() {
        return feedbackJson(inventoryService.listAll());
    }


    @PostMapping("/inventories/")
    public JsonMsg addInventory(Inventory inventory) {
        int prefixQuantity = inventory.getPrefixQuantity();
        for (int i = 0; i < prefixQuantity; i++) {
            inventory.setSn(inventory.getSnPrefix() + StringTool.getSNTail(i + 1, prefixQuantity));
            //System.out.println(inventory.getSn());
            inventory.setBindingState(0);
            // 8位验证码
            inventory.setCheckCode(TokenTool.getRandomString(8));
            inventoryService.addInventory(inventory);
        }
        return new JsonMsg();
    }

    @PostMapping("/inventories/checkSN")
    public JsonMsg checkSN(Inventory inventory) {
        return feedbackJson(inventoryService.checkSN(inventory));
    }


    //这个方法用于设备端更新checkCode之后, 给app扫描验证使用
    @PostMapping("/inventories/updateCheckCode")
    public JsonMsg updateCheckCodeBySN(Inventory inventory) {
        return feedbackJson(inventoryService.updateCheckCodeBySN(inventory));
    }

}
