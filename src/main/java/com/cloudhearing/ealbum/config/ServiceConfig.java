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

    public void setDeviceSecret(String deviceSecret) {
        this.deviceSecret = deviceSecret;
    }

    public String getAppKey() {

        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public void setDeviceAppKey(String deviceAppKey) {
        this.deviceAppKey = deviceAppKey;
    }


    public void setApiAddress(String apiAddress) {
        this.apiAddress = apiAddress;
    }

    public void setResultApiAddress(String resultApiAddress) {
        this.resultApiAddress = resultApiAddress;
    }

    public String getApiAddress() {

        return apiAddress;
    }

    public String getResultApiAddress() {
        return resultApiAddress;
    }


}
