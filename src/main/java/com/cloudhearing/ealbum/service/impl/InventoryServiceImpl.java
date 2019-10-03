package com.cloudhearing.ealbum.service.impl;

import com.cloudhearing.ealbum.entity.Inventory;
import com.cloudhearing.ealbum.entity.User;
import com.cloudhearing.ealbum.mapper.InventoryMapper;
import com.cloudhearing.ealbum.mapper.UserMapper;
import com.cloudhearing.ealbum.service.InventoryService;
import com.cloudhearing.ealbum.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Resource
    InventoryMapper inventoryMapper;

    @Override
    public int addInventory(Inventory inventory) {
        return inventoryMapper.addInventory(inventory);
    }

    @Override
    public Inventory checkSN(Inventory inventory) {
        return inventoryMapper.checkSN(inventory);
    }

    @Override

    public List<Inventory> listAll() {
        return inventoryMapper.listAll();
    }

}
