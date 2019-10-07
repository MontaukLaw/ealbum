package com.cloudhearing.ealbum.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

public class JSONTool {

    //检查输入错误, 避免post过来的数据格式不正确.
    public static boolean checkInputError(JSONObject obj) {
        try {
            //对传过来的json字符串进行包装.
            PushJsonMsgFactory pushJsonMsgFactory = new PushJsonMsgFactory(obj);
        } catch (Exception e) {
            //logger.error(e.toString());
            return true;
        }
        return false;
    }
}
