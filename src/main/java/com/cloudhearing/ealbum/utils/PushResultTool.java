package com.cloudhearing.ealbum.utils;

import com.alibaba.fastjson.JSON;

public class PushResultTool {

    //对结果进行整形处理
    public static JsonMsg reslutProcess(String resultStr) {
        JsonMsg jsonMsg = new JsonMsg();
        if (resultStr.indexOf("error") > 0) {
            return exportErrorMsg(resultStr, "error");
        } else if (resultStr.indexOf("timeout") > 0) {
            return exportErrorMsg(resultStr, "jpush interface access timeout");
        }

        //如果不出错, 转换一下结果
        JpushResponse response = ResponseStringTool.tranString(resultStr);

        jsonMsg.setObj(response);
        return jsonMsg;
    }

    public static JsonMsg exportErrorMsg(String resultStr, String errorType) {

        JsonMsg jsonMsg = new JsonMsg();
        JpushErrorMsg msg = JSON.parseObject(resultStr, JpushErrorMsg.class);
        jsonMsg.setSuccess(false);
        jsonMsg.setMsg(errorType);
        jsonMsg.setObj(msg.getError());
        return jsonMsg;
    }

}
