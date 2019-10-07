package com.cloudhearing.ealbum.utils;

//早期几个接口的极光错误实体类
public class JpushError {
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
        return "JpushError{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
