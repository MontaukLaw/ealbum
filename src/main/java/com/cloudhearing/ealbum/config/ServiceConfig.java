package com.cloudhearing.ealbum.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Value("${jpush.appkey}")
    private String appKey = "";
//
//    @Value("${jpush.device.appkey}")
//    private String deviceAppKey = "";
//
//    @Value("${jpush.device.secret}")
//    private String deviceDeviceAppSecret = "";

    @Value("${jpush.secret}")
    private String secret = "";

    @Value("${jpush.api.address}")
    private String apiAddress = "";

    @Value("${jpush.resultapi.address}")
    private String resultApiAddress = "";
//
//    public String getDeviceAppKey() {
//        return deviceAppKey;
//    }
//
//    public String getDeviceDeviceAppSecret() {
//        return deviceDeviceAppSecret;
//    }

    public String getAppKey() {

        return appKey;
    }

    public String getSecret() {

        return secret;
    }

    public String getApiAddress() {

        return apiAddress;
    }

    public String getResultApiAddress() {
        return resultApiAddress;
    }


    //jpush.appkey: 80a9e1726ae1a17365909b69
    //jpush.secret: fd1c9e8b5bbb3e8f90003871

    //jpush.api.address: "https://bjapi.push.jiguang.cn/v3/push"
    //jpush.resultapi.address: "https://report.jpush.cn/v3/status/message"

}
