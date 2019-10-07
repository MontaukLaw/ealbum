package com.cloudhearing.ealbum.utils;

import java.io.Serializable;

//
public class JpushResponse implements Serializable {

    private static final long serialVersionUID = 4724639590165124377L;

    private int sendno;
    private String msg_id;
    private JpushError errorMsg;

    private boolean ifError = false;

    public JpushError getErrorMsg() {
        return errorMsg;
    }

    public boolean isIfError() {
        return ifError;
    }

    public void setIfError(boolean ifError) {
        this.ifError = ifError;
    }

    public void setErrorMsg(JpushError errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getSendno() {
        return sendno;
    }

    public void setSendno(int sendno) {
        this.sendno = sendno;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    @Override
    public String toString() {
        return "JpushResponse{" +
                "sendno=" + sendno +
                ", msg_id='" + msg_id + '\'' +
                ", errorMsg=" + errorMsg +
                ", ifError=" + ifError +
                '}';
    }
}
