package com.cloudhearing.ealbum.service.impl;

import com.cloudhearing.ealbum.mapper.ResourceAddressMapper;
import com.cloudhearing.ealbum.service.ResourceAddressService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import com.cloudhearing.ealbum.entity.ResourceAddress;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResourceAddressServiceImpl implements ResourceAddressService {

    @Resource
    ResourceAddressMapper resourceAddressMapper;

    @Override
    public List<ResourceAddress> listAll() {
        return resourceAddressMapper.listAll();

    }

    @Override
    public int addResourceAddress(ResourceAddress resourceAddress) {
        return resourceAddressMapper.addResourceAddress(resourceAddress);
    }

    @Override
    public Page<ResourceAddress> findAllByDeviceID(int pageNo, int pageSize, String deviceID){
        PageHelper.startPage(pageNo, pageSize);
        return resourceAddressMapper.findAllByDeviceID(pageNo, pageSize,deviceID);
    }


}
