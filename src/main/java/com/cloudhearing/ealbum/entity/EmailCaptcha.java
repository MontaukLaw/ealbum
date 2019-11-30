package com.cloudhearing.ealbum.entity;


import java.util.Date;

public class EmailCaptcha {

    private String email;
    private String userID;
    private Date applyTime;
    private Date expireAfter;

    private String captchaCode;

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getExpireAfter() {
        return expireAfter;
    }

    public void setExpireAfter(Date expireAfter) {
        this.expireAfter = expireAfter;
    }

    @Override
    public String toString() {
        return "EmailCaptcha{" +
                "email='" + email + '\'' +
                ", userID='" + userID + '\'' +
                ", applyTime=" + applyTime +
                ", expireAfter=" + expireAfter +
                ", captchaCode='" + captchaCode + '\'' +
                '}';
    }
}
