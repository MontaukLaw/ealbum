package com.cloudhearing.ealbum.entity;

import java.util.Date;
import java.util.List;

public class User {

    // 9项
    private String id, username, icon, region;
    private String cellModel, email, password;
    private String cellNumber;
    private Date regTime;

    private List<Device> devices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCellModel() {
        return cellModel;
    }

    public void setCellModel(String cellModel) {
        this.cellModel = cellModel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", icon='" + icon + '\'' +
                ", region='" + region + '\'' +
                ", cellModel='" + cellModel + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", cellNumber=" + cellNumber +
                ", regTime=" + regTime +
                ", devices=" + devices +
                '}';
    }
}
