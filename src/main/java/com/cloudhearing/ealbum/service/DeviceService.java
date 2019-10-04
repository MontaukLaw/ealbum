package com.cloudhearing.ealbum.service;

import com.cloudhearing.ealbum.entity.Device;
import com.cloudhearing.ealbum.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceService {

    List<Device> listAll();

    int addDevice(Device device);

    int updateDevice(Device device);

    int bindDeviceToUser(String userId, String deviceId);

    Device getDeviceByID(Device device);

}
