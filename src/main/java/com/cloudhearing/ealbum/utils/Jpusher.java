package com.cloudhearing.ealbum.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloudhearing.ealbum.config.ServiceConfig;
import com.cloudhearing.okhttp.ApiAccess;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Jpusher {

    @Autowired
    ServiceConfig serviceConfig;

    protected String key, secrete, apiAddress;
    protected JSONObject pushContent;

    protected Logger logger = Logger.getLogger(this.getClass());

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecrete() {
        return secrete;
    }

    public void setSecrete(String secrete) {
        this.secrete = secrete;
    }


    public String getApiAddress() {
        return apiAddress;
    }

    public void setApiAddress(String apiAddress) {
        this.apiAddress = apiAddress;
    }

    public JSONObject getPushContent() {
        return pushContent;
    }

    public void setPushContent(JSONObject pushContent) {
        this.pushContent = pushContent;
    }


    @Override
    public String toString() {
        return "Jpusher{" +
                "key='" + key + '\'' +
                ", secrete='" + secrete + '\'' +
                ", pushContent='" + pushContent + '\'' +
                '}';
    }

    public String push() {

        PushJsonMsgFactory pushJsonMsgFactory = new PushJsonMsgFactory(pushContent);

        //根据配置文件的App配置信息, 访问对应的极光推送接口.
        ApiAccess apiAccess = new ApiAccess(apiAddress, key, secrete);

        String msg = pushJsonMsgFactory.getPushJsonMsg();

        String resultSt = apiAccess.handlePush(msg);

        logger.debug("resultStr string is : " + resultSt);

        return resultSt;

    }
}
