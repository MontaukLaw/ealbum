package com.cloudhearing.ealbum.entity;

import java.util.Date;

public class RandomCheckCode {

    private String randomCode, sn;
    private Date createTime;

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "RandomCheckCode{" +
                "randomCode='" + randomCode + '\'' +
                ", sn='" + sn + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
