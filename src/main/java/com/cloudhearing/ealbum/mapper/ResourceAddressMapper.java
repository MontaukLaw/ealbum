package com.cloudhearing.ealbum.mapper;

import com.cloudhearing.ealbum.entity.ResourceAddress;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResourceAddressMapper {

    List<ResourceAddress> listAll();

    //int addResourceAddress(ResourceAddress resourceAddress,@Param("deviceSN")String deviceSN,@Param("uploaderId")String uploaderId);
    int addResourceAddress(ResourceAddress resourceAddress);

    //List<ResourceAddress> findAllByDeviceID(ResourceAddress resourceAddress);

    Page<ResourceAddress> findAllByDeviceSN(int pageNo, int pageSize, @Param("deviceSN") String deviceSN);

    int removeResourceAddress(ResourceAddress resourceAddress);
}
