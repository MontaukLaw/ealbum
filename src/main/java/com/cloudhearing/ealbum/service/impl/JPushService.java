package com.cloudhearing.ealbum.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cloudhearing.ealbum.config.ServiceConfig;
import com.cloudhearing.ealbum.entity.JPushContent;
import com.cloudhearing.ealbum.service.UserService;
import com.cloudhearing.ealbum.utils.DeviceJpusher;
import com.cloudhearing.ealbum.utils.PushJsonMsgFactory;
import com.cloudhearing.ealbum.utils.UserJpusher;
import com.cloudhearing.okhttp.ApiAccess;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    DeviceJpusher deviceJpusher;

    @Autowired
    UserJpusher userJpusher;

    public void pushBindingSuccessToUser(String userID, String userJpushId) {
/*
        List<String> rids = new ArrayList<>();
        rids.add(userJpushId);
        Map extras = new HashMap();

        extras.put("userId", userID);

        JPushContent jPushContent = new JPushContent(rids, 1, extras);

        UserJpusher userJpusher=new UserJpusher(jPushContent);
        userJpusher.push();
*/
        //pushBindingSuccess(userID, userJpushId, serviceConfig.getAppKey(), serviceConfig.getAppSecret());
    }

    public void pushBindingSuccessToDevice(String userID, String deviceJpushId) {

        List<String> rids = new ArrayList<>();
        rids.add(deviceJpushId);
        Map extras = new HashMap();

        extras.put("userId", userID);

        JPushContent jPushContent = new JPushContent(rids, 1, extras);

        //logger.debug("-------------key is" + serviceConfig.getDeviceAppKey());
        deviceJpusher.init(jPushContent);

        deviceJpusher.push();

        //DeviceJpusher deviceJpusher = new DeviceJpusher(jPushContent);

        //deviceJpusher.push();
    }

    public String push(String key, String secrete, JSONObject jPushContent) {

        PushJsonMsgFactory pushJsonMsgFactory = new PushJsonMsgFactory(jPushContent);

        //根据配置文件的App配置信息, 访问对应的极光推送接口.
        ApiAccess apiAccess = new ApiAccess(serviceConfig.getApiAddress(), key, secrete);

        String msg = pushJsonMsgFactory.getPushJsonMsg();

        String resultSt = apiAccess.handlePush(msg);

        logger.debug("resultStr string is : " + resultSt);

        return resultSt;

    }

    /*
        public void pushMsg(String jpushId, String key, String secrete, int msgType) {
            List<String> rids = new ArrayList<>();
            rids.add(jpushId);
            Map extras = new HashMap();
            extras.put("xx", "12321");
            extras.put("yyy", "7676");

            JPushContent jPushContent = new JPushContent(rids, msgType, extras);

            logger.debug(jPushContent);

            push(key, secrete, jPushContent);

        }
    */

    public void pushBindingSuccess(String userID, String jpushId, String key, String secrete) {


        //push(key, secrete, jPushContent);

    }

    public void pushUnbindingSuccess(String userID, String jpushId, String key, String secrete) {
//        List<String> rids = new ArrayList<>();
//        rids.add(jpushId);
//        Map extras = new HashMap();
//
//        extras.put("userId", userID);
//
//        JPushContent jPushContent = new JPushContent(rids, 2, extras);
//
//        push(key, secrete, jPushContent);

    }


    public void pushUnbindingSuccessToUser(String sn, String userJpushID) {

        List<String> rids = new ArrayList<>();
        rids.add(userJpushID);
        Map extras = new HashMap();
        extras.put("sn", sn);

        JPushContent jPushContent = new JPushContent(rids, 7, extras);

        userJpusher.init(jPushContent);

        userJpusher.push();

    }

    public void pushUnbindingSuccessToDevice(String userID, String deviceJpushID) {
        List<String> rids = new ArrayList<>();
        rids.add(deviceJpushID);
        Map extras = new HashMap();

        extras.put("userId", userID);

        JPushContent jPushContent = new JPushContent(rids, 2, extras);

        deviceJpusher.init(jPushContent);

        deviceJpusher.push();

    }

//    public void pushToUser(String sn, String userJpushID, int resourceType, int messageType) {
//        List<String> rids = new ArrayList<>();
//        rids.add(userJpushID);
//        Map extras = new HashMap();
//
//        extras.put("sn", sn);
//        extras.put("type", resourceType);
//
//        JPushContent jPushContent = new JPushContent(rids, messageType, extras);
//
//        push(serviceConfig.getDeviceAppKey(), serviceConfig.getDeviceSecret(), jPushContent);
//
//    }

    public void pushResourceUpdateToUser(String sn, String userJpushID, int resourceType) {
        List<String> rids = new ArrayList<>();
        rids.add(userJpushID);
        Map extras = new HashMap();

        extras.put("sn", sn);
        extras.put("type", resourceType);

        JPushContent jPushContent = new JPushContent(rids, 8, extras);

        userJpusher.init(jPushContent);
        userJpusher.push();

        //push(serviceConfig.getDeviceAppKey(), serviceConfig.getDeviceSecret(), jPushContent);

    }

    public void pushDeviceUpdateToUser(String sn, String userJpushID) {
        List<String> rids = new ArrayList<>();
        rids.add(userJpushID);
        Map extras = new HashMap();

        extras.put("sn", sn);

        JPushContent jPushContent = new JPushContent(rids, 9, extras);

        userJpusher.init(jPushContent);
        userJpusher.push();
        //push(serviceConfig.getDeviceAppKey(), serviceConfig.getDeviceSecret(), jPushContent);

    }

    public void pushResourceUpdateToDevice(String userID, String deviceJpushID, int resourceType) {
        List<String> rids = new ArrayList<>();
        rids.add(deviceJpushID);
        Map extras = new HashMap();

        extras.put("userId", userID);
        extras.put("type", resourceType);

        JPushContent jPushContent = new JPushContent(rids, 3, extras);

        deviceJpusher.init(jPushContent);

        deviceJpusher.push();
        //push(serviceConfig.getDeviceAppKey(), serviceConfig.getDeviceSecret(), jPushContent);

    }

    public void pushUserUpdateToDevice(String deviceJpushID, String userID) {
        List<String> rids = new ArrayList<>();
        rids.add(deviceJpushID);
        Map extras = new HashMap();

        extras.put("userId", userID);

        JPushContent jPushContent = new JPushContent(rids, 4, extras);

        deviceJpusher.init(jPushContent);

        deviceJpusher.push();
        //push(serviceConfig.getDeviceAppKey(), serviceConfig.getDeviceSecret(), jPushContent);

    }
}

