package com.cloudhearing.ealbum.controller;

import com.cloudhearing.ealbum.entity.Device;
import com.cloudhearing.ealbum.service.DeviceService;
import com.cloudhearing.ealbum.utils.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DeviceController extends BaseController{
    @Autowired
    DeviceService deviceService;

    @PostMapping("/devices")
    public JsonMsg listAllDevices() {
        List<Device> deviceList=deviceService.listAll();

        JsonMsg jsonMsg=feedbackJson(deviceList);

        return jsonMsg;

    }
}
