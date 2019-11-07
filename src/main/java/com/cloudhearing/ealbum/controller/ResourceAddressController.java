package com.cloudhearing.ealbum.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloudhearing.ealbum.entity.Device;
import com.cloudhearing.ealbum.entity.ResourceAddress;
import com.cloudhearing.ealbum.entity.User;
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

    @GetMapping("/resourceAddresses/device/{sn}/pageno={pageno}&pagesize={pagesize}")
    public JsonMsg findByDeviceID(@PathVariable("pageno") int pageNo, @PathVariable("pagesize") int pageSize, @PathVariable("sn") String sn) {
        return feedbackJson(resourceAddressService.findAllByDeviceSN(pageNo, pageSize, sn));
    }

    @RequestMapping(value = "/resourceAddresses/", method = RequestMethod.DELETE)
    public JsonMsg removeResourceAddress(ResourceAddress resourceAddress) {
        int result = resourceAddressService.removeResourceAddress(resourceAddress);
        if (result > 0) {
            String userID = resourceAddress.getUserID();
            if (userID != null) {

                notifyToDeviceByUserID(userID);
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

            notifyToDeviceByUserID(userID);

        } else {

            feedbackErrorJson("Add resource failed");
        }
        return feedbackJson(result);
    }

    @GetMapping("/resourceAddresses/")
    public JsonMsg listAll() {
        return feedbackJson(resourceAddressService.listAll());
    }

    @PostMapping("/resourceAddresses/byJson")
    public JsonMsg pushMsgToDevice(@RequestBody JSONObject obj) throws Exception {
        List<ResourceAddress> resourceAddressList = ResourceAdderssJsonFactory.transResAddrsJsonToEntity(obj);
        logger.debug(resourceAddressList);
        if (resourceAddressList != null) {
            for (int i = 0; i < resourceAddressList.size(); i++) {
                ResourceAddress ra = resourceAddressList.get(i);
                resourceAddressService.addResourceAddress(ra);

                if (ra.getUploader() != null) {

                    User user = userService.getUserDetail(ra.getUploader());

                    if (user != null) {

                        notifyToDeviceByUserID(user.getId());

                    }
                }
            }

        }
        return new JsonMsg();

    }


    void notifyToDeviceByUserID(String userID) {

        String jpushID = userService.getUserJpushID(userID);

        //推送给设备端
        jPushService.pushResourceUpdateToDevice(jpushID);
    }

}
