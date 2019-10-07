package com.cloudhearing.okhttp;

//用于访问极光的推送结果查询接口
//获取之前发送的推送请求的结果
public class ResultApiAccess extends ApiAccess {
    private String ResultApiAddress;

    public ResultApiAccess(String apiAddress, String username, String password) {
        super(apiAddress, username, password);
    }

    public String getResultApiAddress() {
        return ResultApiAddress;
    }

    public void setResultApiAddress(String resultApiAddress) {
        ResultApiAddress = resultApiAddress;
    }


}
