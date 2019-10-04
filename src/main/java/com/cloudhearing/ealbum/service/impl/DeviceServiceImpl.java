package com.cloudhearing.ealbum.service.impl;

import com.cloudhearing.ealbum.entity.Device;
import com.cloudhearing.ealbum.entity.User;
import com.cloudhearing.ealbum.mapper.DeviceMapper;
import com.cloudhearing.ealbum.mapper.UserMapper;
import com.cloudhearing.ealbum.service.DeviceService;
import com.cloudhearing.ealbum.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Resource
    DeviceMapper deviceMapper;


    @Override
    public List<Device> listAll() {
        return deviceMapper.listAll();

    }

    @Override
    public int addDevice(Device device) {
        return deviceMapper.addDevice(device);
    }

    @Override
    public int updateDevice(Device device) {
        return deviceMapper.updateDevice(device);
    }

    @Override
    public int bindDeviceToUser(String userId, String deviceId) {
        return deviceMapper.bindDeviceToUser(userId, deviceId);
    }

    @Override
    public Device getDeviceByID(Device device) {
        return deviceMapper.getDeviceByID(device);
    }

}
