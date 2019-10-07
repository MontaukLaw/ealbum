package com.cloudhearing.ealbum.utils;

import java.util.List;

//极光推送的结果的实体类
public class JpushResult {

    private List<String> registration_ids;
    private long msg_id;

    public List<String> getRegistration_ids() {
        return registration_ids;
    }

    public void setRegistration_ids(List<String> registration_ids) {
        this.registration_ids = registration_ids;
    }

    public long getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(long msg_id) {
        this.msg_id = msg_id;
    }
}
