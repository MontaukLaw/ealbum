package com.cloudhearing.ealbum.utils;

import com.alibaba.fastjson.JSONObject;
import com.cloudhearing.ealbum.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserJpusher extends Jpusher {

    public void init(JSONObject pushContent) {
        this.key = serviceConfig.getAppKey();
        this.secrete = serviceConfig.getAppSecret();
        this.pushContent = pushContent;
        this.apiAddress =serviceConfig.getApiAddress();
    }

}
