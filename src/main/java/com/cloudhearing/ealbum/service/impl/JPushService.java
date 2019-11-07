package com.cloudhearing.ealbum.service.impl;

import com.cloudhearing.ealbum.config.ServiceConfig;
import com.cloudhearing.ealbum.entity.JPushContent;
import com.cloudhearing.ealbum.entity.User;
import com.cloudhearing.ealbum.service.UserService;
import com.cloudhearing.ealbum.utils.JSONTool;
import com.cloudhearing.ealbum.utils.PushJsonMsgFactory;
import com.cloudhearing.okhttp.ApiAccess;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JPushService {

    protected Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    public ServiceConfig serviceConfig;

    @Autowired
    UserService userService;

    public void pushBindingSuccessToUser(String userJpushId) {

        pushBindingSuccess(userJpushId, serviceConfig.getAppKey(), serviceConfig.getAppSecret());
    }

    public void pushBindingSuccessToDevice(String deviceJpushId) {

        pushBindingSuccess(deviceJpushId, serviceConfig.getDeviceAppKey(), serviceConfig.getDeviceSecret());
    }

    public void pushMsg(String jpushId, String key, String secrete, int msgType) {
        List<String> rids = new ArrayList<>();
        rids.add(jpushId);
        Map extras = new HashMap();
        extras.put("xx", "12321");
        extras.put("yyy", "7676");

        JPushContent jPushContent = new JPushContent(rids, msgType, extras);

        logger.debug(jPushContent);

        //根据配置文件的App配置信息, 访问对应的极光推送接口.
        ApiAccess apiAccess = new ApiAccess(serviceConfig.getApiAddress(), key, secrete);

        PushJsonMsgFactory pushJsonMsgFactory = new PushJsonMsgFactory(jPushContent);
        String msg = pushJsonMsgFactory.getPushJsonMsg();

        String resultStr = apiAccess.handlePush(msg);

        logger.debug("resultStr string is : " + resultStr);

    }

    public void pushBindingSuccess(String jpushId, String key, String secrete) {
        pushMsg(jpushId, key, secrete, 1);

    }

    public void pushUnbindingSuccess(String jpushId, String key, String secrete) {
        pushMsg(jpushId, key, secrete, 2);

    }



    public void pushUnbindingSuccessToUser(String jpushID) {
        pushUnbindingSuccess(jpushID, serviceConfig.getAppKey(), serviceConfig.getAppSecret());

    }

    public void pushUnbindingSuccessToDevice(String jpushID) {
        pushUnbindingSuccess(jpushID, serviceConfig.getDeviceAppKey(), serviceConfig.getDeviceSecret());

    }

    public void pushResourceUpdateToDevice(String jpushID){

        pushMsg(jpushID, serviceConfig.getDeviceAppKey(), serviceConfig.getDeviceSecret(), 3);
    }

}
