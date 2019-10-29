package com.cloudhearing.ealbum.controller;

import com.cloudhearing.ealbum.entity.Device;
import com.cloudhearing.ealbum.service.DeviceService;
import com.cloudhearing.ealbum.utils.JsonMsg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DeviceController extends BaseController {
    @Autowired
    DeviceService deviceService;

    @GetMapping("/devices/")
    public JsonMsg listAllDevices() {
        List<Device> deviceList = deviceService.listAll();

        JsonMsg jsonMsg = feedbackJson(deviceList);

        return jsonMsg;
    }

    @GetMapping("/devices/sn/{sn}")
    public JsonMsg getDeviceBySN(@PathVariable("sn") String sn) {
        Device device = new Device();
        device.setSn(sn);

        JsonMsg jsonMsg = feedbackJson(deviceService.getDeviceBySN(device));

        return jsonMsg;
    }

//    @GetMapping("/devices/{id}")
//    public JsonMsg getDeviceByID(@PathVariable("id") String deviceId) {
//        Device device = new Device();
//        device.setId(deviceId);
//
//        JsonMsg jsonMsg = feedbackJson(deviceService.getDeviceByID(device));
//
//        return jsonMsg;
//    }

    @RequestMapping(value = "/devices/", method = RequestMethod.PUT)
    public JsonMsg updateDevice(Device device) {

        JsonMsg jsonMsg = feedbackJson(deviceService.updateDevice(device));

        return jsonMsg;
    }

    @PostMapping("/devices/")
    public JsonMsg addDevice(Device device) {

        JsonMsg jsonMsg = feedbackJson(deviceService.addDevice(device));

        return jsonMsg;

    }

    @PostMapping("/devices/binding")
    public JsonMsg bindDeviceToUser(@Param("userId") String userId, @Param("deviceSN") String deviceSN) {

        JsonMsg jsonMsg = feedbackJson(deviceService.bindDeviceToUser(userId, deviceSN));

        return jsonMsg;
    }

    @RequestMapping(value = "/devices/binding",method = RequestMethod.DELETE)
    public JsonMsg removeBindg(@Param("userId") String userId, @Param("deviceSN") String deviceSN) {

        JsonMsg jsonMsg = feedbackJson(deviceService.removeBinding(userId, deviceSN));

        return jsonMsg;
    }

}
