package com.cloudhearing.ealbum.utils;


//极光推送的错误信息的实体类
public class ErrorMsg {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorMsg{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
