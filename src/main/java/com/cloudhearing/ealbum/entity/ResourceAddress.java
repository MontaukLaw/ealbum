package com.cloudhearing.ealbum.entity;

import java.util.Date;

public class ResourceAddress {
    private String id, resourceAddress, deviceId;
    private long space;
    private String storageId;
    private Date createTime;
    private int state, type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceAddress() {
        return resourceAddress;
    }

    public void setResourceAddress(String resourceAddress) {
        this.resourceAddress = resourceAddress;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public long getSpace() {
        return space;
    }

    public void setSpace(long space) {
        this.space = space;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    @Override
    public String toString() {
        return "ResourceAddress{" +
                "id='" + id + '\'' +
                ", resourceAddress='" + resourceAddress + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", space=" + space +
                ", storageId=" + storageId +
                ", createTime=" + createTime +
                ", state=" + state +
                ", type=" + type +
                '}';
    }
}
