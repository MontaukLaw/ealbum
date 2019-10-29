package com.cloudhearing.ealbum.entity;

import java.util.Date;
import java.util.List;

public class Device {
    private String region, model, sn, firmwareVersion;
    private int usedSpace, totalSpace;
    private String email, jpushId;
    private Date activateTime, lastOnlineTime;

    private List<User> owners;

    public List<User> getOwners() {
        return owners;
    }

    public void setOwners(List<User> owners) {
        this.owners = owners;
    }

    public String getJpushId() {
        return jpushId;
    }

    public void setJpushId(String jpushId) {
        this.jpushId = jpushId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public int getUsedSpace() {
        return usedSpace;
    }

    public void setUsedSpace(int usedSpace) {
        this.usedSpace = usedSpace;
    }

    public int getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(int totalSpace) {
        this.totalSpace = totalSpace;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(Date activateTime) {
        this.activateTime = activateTime;
    }

    public Date getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(Date lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }

    @Override
    public String toString() {
        return "Device{" +
                ", region='" + region + '\'' +
                ", model='" + model + '\'' +
                ", sn='" + sn + '\'' +
                ", firmwareVersion='" + firmwareVersion + '\'' +
                ", usedSpace=" + usedSpace +
                ", totalSpace=" + totalSpace +
                ", email='" + email + '\'' +
                ", jpushId='" + jpushId + '\'' +
                ", activateTime=" + activateTime +
                ", lastOnlineTime=" + lastOnlineTime +
                ", owners=" + owners +
                '}';
    }
}
