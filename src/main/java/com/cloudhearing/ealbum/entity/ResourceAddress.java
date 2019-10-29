package com.cloudhearing.ealbum.entity;

import java.util.Date;

public class ResourceAddress {
    private String id, resourceAddress;
    private long space;
    private String storageId;
    private Date createTime;
    private int state, type;
    private User uploader;
    private String previewPic;
    private Device device;
    private String userID, deviceSN;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDeviceSN() {
        return deviceSN;
    }

    public void setDeviceSN(String deviceSN) {
        this.deviceSN = deviceSN;
    }

    public User getUploader() {
        return uploader;
    }

    public String getPreviewPic() {
        return previewPic;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    public void setPreviewPic(String previewPic) {
        this.previewPic = previewPic;
    }

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
                ", space=" + space +
                ", storageId='" + storageId + '\'' +
                ", createTime=" + createTime +
                ", state=" + state +
                ", type=" + type +
                ", uploader=" + uploader +
                ", previewPic='" + previewPic + '\'' +
                ", device=" + device +
                ", userID='" + userID + '\'' +
                ", deviceSN='" + deviceSN + '\'' +
                '}';
    }
}
