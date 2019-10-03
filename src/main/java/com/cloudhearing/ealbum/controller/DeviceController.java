package com.cloudhearing.ealbum.controller;

import com.cloudhearing.ealbum.entity.Device;
import com.cloudhearing.ealbum.service.DeviceService;
import com.cloudhearing.ealbum.utils.JsonMsg;
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


}
