package com.cloudhearing.ealbum.mapper;

import com.cloudhearing.ealbum.entity.Inventory;
import com.cloudhearing.ealbum.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InventoryMapper {

    int addInventory(Inventory inventory);

    Inventory checkSN(Inventory inventory);

    List<Inventory> listAll();

    int updateCheckCodeBySN(Inventory inventory);

}
