package com.cloudhearing.ealbum.service;

import com.cloudhearing.ealbum.entity.ResourceAddress;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public interface ResourceAddressService {

    List<ResourceAddress> listAll();

    int addResourceAddress(ResourceAddress resourceAddress);

    //int addResourceAddress(ResourceAddress resourceAddress, String deviceSN, String uploaderId);

    //List<ResourceAddress> findAllByDeviceID(ResourceAddress resourceAddress);

    Page<ResourceAddress> findAllByDeviceSN(int pageNo, int pageSize, String deviceSN);

    Page<ResourceAddress> findAllByDeviceSNWithFilter(int pageNo, int pageSize, String deviceSN, String userid, int type);

    //int removeResourceAddress(ResourceAddress resourceAddress);

    int removeResourceAddress(ResourceAddress resourceAddress);

    ResourceAddress findResourceById(ResourceAddress resourceAddress);
}
