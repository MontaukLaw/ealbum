package com.cloudhearing.ealbum.controller;

import com.cloudhearing.ealbum.entity.Device;
import com.cloudhearing.ealbum.entity.ResourceAddress;
import com.cloudhearing.ealbum.entity.User;
import com.cloudhearing.ealbum.service.ResourceAddressService;
import com.cloudhearing.ealbum.utils.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ResourceAddressController extends BaseController {
    @Autowired
    ResourceAddressService resourceAddressService;

    @GetMapping("/resourceAddresses/device/{sn}/pageno={pageno}&pagesize={pagesize}")
    public JsonMsg findByDeviceID(@PathVariable("pageno") int pageNo, @PathVariable("pagesize") int pageSize, @PathVariable("sn") String sn) {
        return feedbackJson(resourceAddressService.findAllByDeviceSN(pageNo, pageSize, sn));
    }

    @RequestMapping(value = "/resourceAddresses/", method = RequestMethod.DELETE)
    public JsonMsg removeResourceAddress(ResourceAddress resourceAddress) {
        return feedbackJson(resourceAddressService.removeResourceAddress(resourceAddress));
    }

    @PostMapping("/resourceAddresses/user/{userID}/device/{sn}")
    public JsonMsg addResourceAddress(ResourceAddress resourceAddress, @PathVariable("userID") String userID, @PathVariable("sn") String sn) {
        User user = new User();
        user.setId(userID);
        resourceAddress.setUploader(user);

        Device device = new Device();
        device.setSn(sn);
        resourceAddress.setDevice(device);
        return feedbackJson(resourceAddressService.addResourceAddress(resourceAddress));
    }

    @GetMapping("/resourceAddresses/")
    public JsonMsg listAll() {
        return feedbackJson(resourceAddressService.listAll());
    }

}
