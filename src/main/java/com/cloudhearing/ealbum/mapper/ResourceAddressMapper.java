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

    Page<ResourceAddress> findAllByDeviceSN(int pageNo, int pageSize, @Param("deviceSN") String deviceSN);

    int removeResourceAddress(ResourceAddress resourceAddress);

    Page<ResourceAddress> findAllByDeviceSNWithFilter(int pageNo, int pageSize, @Param("deviceSN") String deviceSN, @Param("userid") String userid, @Param("type") int type);

    ResourceAddress findResourceById(ResourceAddress resourceAddress);
}
