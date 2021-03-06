package com.cloudhearing.ealbum.service.impl;

import com.cloudhearing.ealbum.entity.Device;
import com.cloudhearing.ealbum.entity.User;
import com.cloudhearing.ealbum.mapper.DeviceMapper;
import com.cloudhearing.ealbum.mapper.UserMapper;
import com.cloudhearing.ealbum.service.DeviceService;
import com.cloudhearing.ealbum.service.UserService;
import org.apache.ibatis.annotations.Param;
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
    public int bindDeviceToUser(String userId, String deviceSN) {
        return deviceMapper.bindDeviceToUser(userId, deviceSN);
    }

    @Override
    public Device getDeviceBySN(Device device) {
        return deviceMapper.getDeviceBySN(device);
    }

    @Override
    public int removeBinding(String userId, String deviceSN) {
        return deviceMapper.removeBinding(userId, deviceSN);
    }

    @Override
    public int bindingCheck(String userId, String sn) {
        return deviceMapper.bindingCheck(userId, sn);
    }

    @Override
    public String getDeviceJpushID(String sn) {
        return deviceMapper.getDeviceJpushID(sn);
    }

    @Override
    public Device getDeviceByEmail(Device device) {
        return deviceMapper.getDeviceByEmail(device);
    }

    @Override
    public int updateDeviceName(Device device) {
        return deviceMapper.updateDeviceName(device);
    }

}
