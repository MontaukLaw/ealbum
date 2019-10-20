package com.cloudhearing.ealbum.mapper;

import com.cloudhearing.ealbum.entity.ResourceAddress;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResourceAddressMapper {

    List<ResourceAddress> listAll();

    int addResourceAddress(ResourceAddress resourceAddress);

    //List<ResourceAddress> findAllByDeviceID(ResourceAddress resourceAddress);

    Page<ResourceAddress> findAllByDeviceID(int pageNo, int pageSize, @Param("deviceId") String deviceID);
}
