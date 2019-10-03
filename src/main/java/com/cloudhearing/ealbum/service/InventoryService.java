package com.cloudhearing.ealbum.service;

import com.cloudhearing.ealbum.entity.Inventory;
import com.cloudhearing.ealbum.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventoryService {

    int addInventory(Inventory inventory);

    Inventory checkSN(Inventory inventory);

    List<Inventory>listAll();
}
