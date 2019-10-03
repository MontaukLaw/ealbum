package com.cloudhearing.ealbum.entity;

import java.util.Date;

public class Inventory {

    private String sn, snPrefix, checkCode;
    private Date createDate;
    private int prefixQuantity, bindingState;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSnPrefix() {
        return snPrefix;
    }

    public void setSnPrefix(String snPrefix) {
        this.snPrefix = snPrefix;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getPrefixQuantity() {
        return prefixQuantity;
    }

    public void setPrefixQuantity(int prefixQuantity) {
        this.prefixQuantity = prefixQuantity;
    }

    public int getBindingState() {
        return bindingState;
    }

    public void setBindingState(int bindingState) {
        this.bindingState = bindingState;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "sn='" + sn + '\'' +
                ", snPrefix='" + snPrefix + '\'' +
                ", checkCode='" + checkCode + '\'' +
                ", createDate=" + createDate +
                ", prefixQuantity=" + prefixQuantity +
                ", bindingState=" + bindingState +
                '}';
    }
}
