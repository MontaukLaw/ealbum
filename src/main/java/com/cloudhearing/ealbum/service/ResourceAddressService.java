package com.cloudhearing.ealbum.service;

import com.cloudhearing.ealbum.entity.ResourceAddress;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public interface ResourceAddressService {

    List<ResourceAddress> listAll();

    int addResourceAddress(ResourceAddress resourceAddress);

    //List<ResourceAddress> findAllByDeviceID(ResourceAddress resourceAddress);

    Page<ResourceAddress> findAllByDeviceID(int pageNo, int pageSize, String deviceID);

}
