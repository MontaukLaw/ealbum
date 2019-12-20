package com.cloudhearing.ealbum.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloudhearing.ealbum.entity.*;
import com.cloudhearing.ealbum.service.DeviceService;
import com.cloudhearing.ealbum.service.ResourceAddressService;
import com.cloudhearing.ealbum.service.UserService;
import com.cloudhearing.ealbum.service.impl.JPushService;
import com.cloudhearing.ealbum.utils.JsonMsg;
import com.cloudhearing.ealbum.utils.ResourceAdderssJsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ResourceAddressController extends BaseController {
    @Autowired
    ResourceAddressService resourceAddressService;

    @Autowired
    UserService userService;

    @Autowired
    JPushService jPushService;

    @Autowired
    DeviceService deviceService;

    @GetMapping("/resourceAddresses/device/{sn}/pageno={pageno}&pagesize={pagesize}")
    public JsonMsg findByDeviceID(ResourceAddress resourceAddress, @PathVariable("pageno") int pageNo, @PathVariable("pagesize") int pageSize, @PathVariable("sn") String sn) {
        System.out.println(resourceAddress);
        return feedbackJson(resourceAddressService.findAllByDeviceSN(pageNo, pageSize, sn));
    }

    @GetMapping("/resourceAddresses/device/{sn}/")
    public JsonMsg findByDeviceID(ResourceAddress resourceAddress, PageData pageData, @PathVariable("sn") String sn) {

        //System.out.println(resourceAddress);
        //System.out.println(pageData);
        return feedbackJson(resourceAddressService.findAllByDeviceSNWithFilter(pageData.getPageno(), pageData.getPagesize(), sn, resourceAddress.getUserID(), resourceAddress.getType()));

    }

    @RequestMapping(value = "/resourceAddresses/", method = RequestMethod.DELETE)
    public JsonMsg removeResourceAddress(ResourceAddress resourceAddress) {
        ResourceAddress resourceAddressInDB = resourceAddressService.findResourceById(resourceAddress);
        String deviceSN = resourceAddressInDB.getDevice().getSn();
        String userID = resourceAddressInDB.getUploader().getId();
        int result = resourceAddressService.removeResourceAddress(resourceAddress);
        if (result > 0) {
            if (userID != null && deviceSN != null) {
                //User inputUser = new User();
                //inputUser.setId(userID);
                //User userInDB = userService.getUserDetail(inputUser);
                //List<Device> deviceList = userInDB.getDevices();
                // for (int i = 0; i < deviceList.size(); i++) {
                // Device device = deviceList.get(i);
                //String deviceJpush = deviceService.getDeviceJpushID(device.getSn());
                String userJpushID = userService.getUserJpushID(userID);
                jPushService.pushResourceUpdateToUser(deviceSN, userJpushID, resourceAddress.getType());
                //}

                //notifyToDeviceByUserID(userID,resourceAddress.getType());
            }

        } else {
            return feedbackErrorJson("Remove failed");
        }
        return feedbackJson("Resource removed");

    }


    @PostMapping("/resourceAddresses/user/{userID}/device/{sn}")
    public JsonMsg addResourceAddress(ResourceAddress resourceAddress, @PathVariable("userID") String userID, @PathVariable("sn") String sn) {
        User user = new User();
        user.setId(userID);
        resourceAddress.setUploader(user);

        Device device = new Device();
        device.setSn(sn);
        resourceAddress.setDevice(device);
        int result = resourceAddressService.addResourceAddress(resourceAddress);
        if (result > 0) {

            //这个接口现在只有邮箱处理会用, 去掉推送功能
            //notifyToDeviceByUserID(userID, sn, resourceAddress.getType());

        } else {

            feedbackErrorJson("Add resource failed");
        }
        return feedbackJson(result);
    }

    @GetMapping("/resourceAddresses/")
    public JsonMsg listAll() {
        return feedbackJson(resourceAddressService.listAll());
    }

    @RequestMapping(value = "/resourceAddresses/byJson", method = RequestMethod.DELETE)
    public JsonMsg removeResourceFromJson(@RequestBody JSONObject obj) throws Exception {
        List<ResourceAddress> resourceAddressList = ResourceAdderssJsonFactory.transResAddrsJsonToEntity(obj);
        int result = 0;
        ResourceAddress ra = null;
        String userID = null;
        String deviceSN = null;
        ResourceAddress resourceAddressInDB = null;
        if (resourceAddressList != null) {
            for (int i = 0; i < resourceAddressList.size(); i++) {
                ra = resourceAddressList.get(i);

                resourceAddressInDB = resourceAddressService.findResourceById(ra);

                result = result + resourceAddressService.removeResourceAddress(ra);

            }

            if (result > 0) {

                if (resourceAddressInDB != null) {

                    userID = resourceAddressInDB.getUploader().getId();

                    deviceSN = resourceAddressInDB.getDevice().getSn();

                    logger.debug(userID + deviceSN);

                    if (userID != null && deviceSN != null) {

                        String userJpushID = userService.getUserJpushID(userID);

                        jPushService.pushResourceUpdateToUser(deviceSN, userJpushID, resourceAddressInDB.getType());

                        return feedbackJson("removed.");
                    }

                }

                //resourceAddressInDB = resourceAddressService.findResourceById(ra);

            }

        }

        return feedbackErrorJson("Remove failed");

    }


    @PostMapping("/resourceAddresses/byJson")
    public JsonMsg addResourceFromJson(@RequestBody JSONObject obj) throws Exception {
        List<ResourceAddress> resourceAddressList = ResourceAdderssJsonFactory.transResAddrsJsonToEntity(obj);
        logger.debug(resourceAddressList);
        int result = 0;
        User user = null;
        Device device = null;
        ResourceAddress ra = null;
        if (resourceAddressList != null) {
            for (int i = 0; i < resourceAddressList.size(); i++) {
                ra = resourceAddressList.get(i);
                device = ra.getDevice();
                result = resourceAddressService.addResourceAddress(ra);

                if (ra.getUploader() != null) {

                    user = userService.getUserDetail(ra.getUploader());

                    if (user != null) {

                        List<Device> devices = user.getDevices();

                        //device = devices.get(0);
                    }
                }
            }

            if (result > 0) {
                if (user != null && device != null) {
                    notifyToDeviceByUserID(user.getId(), device.getSn(), ra.getType());
                }
            }

        }
        return new JsonMsg();
    }


    void notifyToDeviceByUserID(String userID, String deviceSN, int resourceType) {

        //String jpushID = userService.getUserJpushID(userID);
        String deviceJpushID = deviceService.getDeviceJpushID(deviceSN);
        //推送给设备端
        jPushService.pushResourceUpdateToDevice(userID, deviceJpushID, resourceType);
    }

}
