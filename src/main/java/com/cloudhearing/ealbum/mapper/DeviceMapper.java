package com.cloudhearing.ealbum.mapper;

import com.cloudhearing.ealbum.entity.Device;
import com.cloudhearing.ealbum.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceMapper {

    List<Device> listAll();

    int addDevice(Device device);

    int updateDevice(Device device);
}