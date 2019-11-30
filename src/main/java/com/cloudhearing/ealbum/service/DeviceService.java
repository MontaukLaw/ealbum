package com.cloudhearing.ealbum.service;

import com.cloudhearing.ealbum.entity.Device;
import com.cloudhearing.ealbum.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceService {

    List<Device> listAll();

    int addDevice(Device device);

    int updateDevice(Device device);

    int bindDeviceToUser(String userId, String deviceSN);

//    Device getDeviceByID(Device device);

    Device getDeviceBySN(Device device);

    int removeBinding(String userId, String deviceSN);

    int bindingCheck(String userId, String sn);

    String getDeviceJpushID(String sn);

    Device getDeviceByEmail(Device device);



}
