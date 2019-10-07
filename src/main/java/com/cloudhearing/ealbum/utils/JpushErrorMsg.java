package com.cloudhearing.ealbum.utils;

//极光的错误信息的实体类
public class JpushErrorMsg {
    //{"error":{"code":1003,"message":"The registration_id 140fe1da9ee1669587 is invalid!"}}
    private ErrorMsg error;

    public ErrorMsg getError() {
        return error;
    }

    public void setError(ErrorMsg error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "JpushErrorMsg{" +
                "error=" + error +
                '}';
    }
}
