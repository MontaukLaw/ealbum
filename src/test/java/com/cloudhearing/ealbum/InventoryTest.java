package com.cloudhearing.ealbum;


import com.cloudhearing.ealbum.entity.Inventory;
import com.cloudhearing.ealbum.service.DeviceService;
import com.cloudhearing.ealbum.service.InventoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryTest {

    @Resource
    InventoryService integerService;

    @Test
    public void addInventory() {

        Inventory inventory = new Inventory();

        inventory.setBindingState(0);
        inventory.setCheckCode("xxx");
        inventory.setPrefixQuantity(111);
        inventory.setSn("sn1112");
        inventory.setSnPrefix("sn");
        System.out.println(integerService.addInventory(inventory));
    }

    @Test
    public void listAllTest(){

        System.out.println(integerService.listAll());
    }

    @Test
    public void testCheckSN(){
        Inventory inventory=new Inventory();
        inventory.setCheckCode("xxx");
        inventory.setSn("sn1111");
        System.out.println(integerService.checkSN(inventory));

    }

}
