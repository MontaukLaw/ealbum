package com.cloudhearing.ealbum.controller;

import com.cloudhearing.ealbum.entity.Device;
import com.cloudhearing.ealbum.entity.User;
import com.cloudhearing.ealbum.service.DeviceService;
import com.cloudhearing.ealbum.service.UserService;
import com.cloudhearing.ealbum.service.impl.JPushService;
import com.cloudhearing.ealbum.utils.JsonMsg;
import com.cloudhearing.ealbum.utils.TokenTool;
import com.cloudhearing.okhttp.RegMailBox;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/devices/email/{email}")
    public JsonMsg getDeviceByEmail(@PathVariable("email") String email) {

        Device device = new Device();
        device.setEmail(email);

        return feedbackJson(deviceService.getDeviceByEmail(device));
    }

    @GetMapping("/devices/sn/{sn}")
    public JsonMsg getDeviceBySN(@PathVariable("sn") String sn) {
        Device device = new Device();
        device.setSn(sn);

        JsonMsg jsonMsg = feedbackJson(deviceService.getDeviceBySN(device));

        return jsonMsg;
    }

    private void notifyDeviceUpdateToUsers(Device device) {
        List<User> userList = deviceService.getDeviceBySN(device).getOwners();
        for (int i = 0; i < userList.size(); i++) {
            String userJpushID = userList.get(i).getJpushId();

            jPushService.pushDeviceUpdateToUser(device.getSn(), userJpushID);

        }
    }

    @RequestMapping(value = "/devices/name", method = RequestMethod.PUT)
    public JsonMsg updateDeviceName(Device device) {
        int result = deviceService.updateDeviceName(device);

        if (result > 0) {
            notifyDeviceUpdateToUsers(device);

        } else {
            return feedbackErrorJson("Device update failed");
        }

        return feedbackJson(result);

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
        int result = deviceService.updateDevice(device);

        if (result > 0) {
            notifyDeviceUpdateToUsers(device);

        } else {
            return feedbackErrorJson("Device update failed");
        }

        return feedbackJson(result);
    }

    @PostMapping("/devices/")
    public JsonMsg addDevice(Device device) throws Exception {

        //magic number, I like it
        String emailName = TokenTool.getRandomUpcaseChars(8);

        if (RegMailBox.regMailbox(emailName)) {
            device.setEmail(emailName);

            if (deviceService.addDevice(device) > 0) {
                return feedbackJson(emailName);
            }
            return feedbackErrorJson("Insert db failed");
        }


        return feedbackErrorJson("Reg email failed");

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
                //jPushService.pushBindingSuccessToUser(userId, userJpushId);
                jPushService.pushBindingSuccessToDevice(userId, deviceJpushID);
            }
        }

        JsonMsg jsonMsg = feedbackJson(result);

        return jsonMsg;
    }

    public JsonMsg removeBind(String userId, String deviceSN) {

        JsonMsg jsonMsg = feedbackJson(deviceService.removeBinding(userId, deviceSN));

        return jsonMsg;
    }


    @RequestMapping(value = "/devices/binding/fromDevice", method = RequestMethod.DELETE)
    public JsonMsg removeBindFromApp(@Param("userId") String userId, @Param("deviceSN") String deviceSN) {

        if (deviceService.removeBinding(userId, deviceSN) > 0) {
            String userJpushID = userService.getUserJpushID(userId);
            //String deviceJpushID = deviceService.getDeviceJpushID(deviceSN);
            jPushService.pushUnbindingSuccessToUser(deviceSN, userJpushID);

        } else {

            return feedbackErrorJson("Unbind fail.");
        }


        return feedbackJson("Unbind success");
    }

    @RequestMapping(value = "/devices/binding/fromApp", method = RequestMethod.DELETE)
    public JsonMsg removeBindFromDevice(@Param("userId") String userId, @Param("deviceSN") String deviceSN) {

        if (deviceService.removeBinding(userId, deviceSN) > 0) {

            String deviceJpushID = deviceService.getDeviceJpushID(deviceSN);

            jPushService.pushUnbindingSuccessToDevice(userId, deviceJpushID);
        } else {
            return feedbackErrorJson("Unbind fail.");
        }

        return feedbackJson("Unbind success");
    }
}
