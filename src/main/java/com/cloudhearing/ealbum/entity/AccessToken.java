package com.cloudhearing.ealbum.entity;

import java.util.Date;

public class AccessToken {
    private String token;
    private String username;
    private Date createTime;
    private Date availableBefore;
    private int status;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getAvailableBefore() {
        return availableBefore;
    }

    public void setAvailableBefore(Date availableBefore) {
        this.availableBefore = availableBefore;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", createTime=" + createTime +
                ", availableBefore=" + availableBefore +
                ", status=" + status +
                '}';
    }
}
