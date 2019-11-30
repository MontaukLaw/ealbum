package com.cloudhearing.ealbum.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Value("${jpush.app.appkey}")
    private String appKey = "";

    @Value("${jpush.app.secret}")
    private String appSecret = "";

    @Value("${jpush.device.appkey}")
    private String deviceAppKey = "";

    @Value("${jpush.device.secret}")
    private String deviceSecret = "";

    @Value("${jpush.api.address}")
    private String apiAddress = "";

    @Value("${jpush.resultapi.address}")
    private String resultApiAddress = "";

    public String getDeviceAppKey() {
        return deviceAppKey;
    }

    public String getDeviceSecret() {
        return deviceSecret;
    }

    public String getAppKey() {

        return appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getApiAddress() {

        return apiAddress;
    }

    public String getResultApiAddress() {
        return resultApiAddress;
    }


}
