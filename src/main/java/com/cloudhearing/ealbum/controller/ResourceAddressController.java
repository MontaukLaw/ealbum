package com.cloudhearing.ealbum.controller;

import com.cloudhearing.ealbum.entity.ResourceAddress;
import com.cloudhearing.ealbum.service.ResourceAddressService;
import com.cloudhearing.ealbum.utils.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResourceAddressController extends BaseController {
    @Autowired
    ResourceAddressService resourceAddressService;

    @GetMapping("/resourceAddresses/device/{deviceid}/pageno={pageno}&pagesize={pagesize}")
    public JsonMsg findByDeviceID(@PathVariable("pageno") int pageNo, @PathVariable("pagesize") int pageSize, @PathVariable("deviceid") String deviceId) {
        return feedbackJson(resourceAddressService.findAllByDeviceID(pageNo, pageSize, deviceId));

    }


}
