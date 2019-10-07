package com.cloudhearing.ealbum.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ResponseStringTool {

    public static JpushResponse tranString(String responseString){

        JpushResponse response = new JpushResponse();
        String[] splitStr = responseString
                .replaceAll("\"", "")
                .replaceAll("\\{", "")
                .replaceAll("}", "")
                .split(",");

        //检查是否返回错误信息.
        if (responseString.indexOf("error") > 0) {
            //System.out.println("ERROR!");

            JpushErrorMsg jpushErrorMsg=new JpushErrorMsg();

            JSONObject obj= JSON.parseObject(responseString);
            JSONObject errorObj= obj.getJSONObject("error");

            String[] errorInfo = splitStr[0].split(":");

            JpushError jpushError = new JpushError();
            response.setIfError(true);

            jpushError.setCode(errorObj.getInteger("code"));
            jpushError.setMessage(errorObj.getString("message"));
            response.setErrorMsg(jpushError);

        } else {
            String[] sendnoStr = splitStr[0].split(":");
            response.setSendno(Integer.valueOf(sendnoStr[1]));
            String[] mesIdStr = splitStr[1].split(":");
            response.setMsg_id(mesIdStr[1]);

        }

        return response;
    }
}