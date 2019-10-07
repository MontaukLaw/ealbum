package com.cloudhearing.ealbum.utils;

import com.alibaba.fastjson.JSON;

import java.util.List;

//推送结果的工厂类
public class JpushResultFactory {
    JpushResult jpushResult;

    public JpushResultFactory(JpushResponse response, List<String> rids) {
        String msgID = response.getMsg_id();
        jpushResult = new JpushResult();
        jpushResult.setMsg_id(Long.valueOf(msgID));
        jpushResult.setRegistration_ids(rids);
    }

    public String getPushJsonMsg() {
        String result = JSON.toJSONString(jpushResult);
        return result;
    }
}
