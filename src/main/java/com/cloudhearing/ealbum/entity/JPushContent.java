package com.cloudhearing.ealbum.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public class JPushContent extends JSONObject {

    public JPushContent(List<String> registration_id, int message_type, Map extras) {
        super();
        this.put("registration_id", registration_id);
        this.put("message_type", message_type);
        this.put("extras",extras);

    }

}
