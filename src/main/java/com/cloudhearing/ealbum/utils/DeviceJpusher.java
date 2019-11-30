package com.cloudhearing.ealbum.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class DeviceJpusher extends Jpusher {

    public void init(JSONObject pushContent) {
        this.key = this.serviceConfig.getDeviceAppKey();
        this.secrete = this.serviceConfig.getDeviceSecret();
        this.pushContent = pushContent;
        this.apiAddress = this.serviceConfig.getApiAddress();

    }


}
