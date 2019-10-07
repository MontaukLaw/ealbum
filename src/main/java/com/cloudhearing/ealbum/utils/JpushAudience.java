package com.cloudhearing.ealbum.utils;

import java.util.List;

//极光的Audience实体类
public class JpushAudience {
    private List<String> registration_id;

    public List<String> getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(List<String> registration_id) {
        this.registration_id = registration_id;
    }
}
