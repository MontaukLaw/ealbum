package com.cloudhearing.ealbum.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloudhearing.ealbum.entity.Device;
import com.cloudhearing.ealbum.entity.JPushContent;
import com.cloudhearing.ealbum.entity.User;
import com.cloudhearing.ealbum.service.DeviceService;
import com.cloudhearing.ealbum.service.UserService;
import com.cloudhearing.ealbum.service.impl.JPushService;
import com.cloudhearing.ealbum.utils.JsonMsg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DeviceController extends BaseController {
    @Autowired
    DeviceService deviceService;

    @Autowired
    UserService userService;

    @Autowired
    JPushService jPushService;

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

        String userJpushId;
        int result;
        User inputUser = new User();
        inputUser.setId(userId);
        User userInDB = userService.getUserDetail(inputUser);
        if (userInDB != null) {
            userJpushId = userInDB.getJpushId();
            if (userJpushId == null || userJpushId.equals("")) {
                return feedbackErrorJson("User didn't update Jpush ID.");
            }

        } else {
            return feedbackErrorJson("No such an user.");
        }

        Device inputDevice = new Device();
        inputDevice.setSn(deviceSN);
        Device deviceInDB = deviceService.getDeviceBySN(inputDevice);
        String deviceJpushID;
        if (deviceInDB != null) {
            deviceJpushID = deviceInDB.getJpushId();
            if (deviceJpushID == null || deviceJpushID.equals("")) {
                return feedbackErrorJson("Device didn't update Jpush ID.");
            }

        } else {
            return feedbackErrorJson("No such a device.");
        }

        if (deviceService.bindingCheck(userId, deviceSN) > 0) {

            return feedbackErrorJson("Binding relationship already exist.");
        } else {

            result = deviceService.bindDeviceToUser(userId, deviceSN);

            if (result > 0) {
                jPushService.pushBindingSuccessToUser(userJpushId);
                jPushService.pushBindingSuccessToDevice(deviceJpushID);
            }
        }

        JsonMsg jsonMsg = feedbackJson(result);

        return jsonMsg;
    }

    public JsonMsg removeBind(String userId, String deviceSN) {

        JsonMsg jsonMsg = feedbackJson(deviceService.removeBinding(userId, deviceSN));

        return jsonMsg;
    }


    @RequestMapping(value = "/devices/binding/fromApp", method = RequestMethod.DELETE)
    public JsonMsg removeBindFromApp(@Param("userId") String userId, @Param("deviceSN") String deviceSN) {

        if (deviceService.removeBinding(userId, deviceSN) > 0) {
            String userJpushID = userService.getUserJpushID(userId);

            jPushService.pushUnbindingSuccessToUser(userJpushID);

        } else {

            return feedbackErrorJson("Unbind fail.");
        }


        return feedbackJson("Unbind success");
    }

    @RequestMapping(value = "/devices/binding/fromDevice", method = RequestMethod.DELETE)
    public JsonMsg removeBindFromDevice(@Param("userId") String userId, @Param("deviceSN") String deviceSN) {

        if (deviceService.removeBinding(userId, deviceSN) > 0) {

            String deviceJpushID = deviceService.getDeviceJpushID(deviceSN);

            jPushService.pushUnbindingSuccessToDevice(deviceJpushID);
        } else {
            return feedbackErrorJson("Unbind fail.");
        }

        return feedbackJson("Unbind success");
    }
}
