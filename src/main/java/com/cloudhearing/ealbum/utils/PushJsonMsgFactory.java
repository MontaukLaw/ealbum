package com.cloudhearing.ealbum.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;


/*
 * 这个类将用户post过来的json字符串进行打包, 通过Ali的fastjson包对json数据提取rid, type等数据.
 * */
public class PushJsonMsgFactory {
    ArrayList<String> rArrayList;
    JpushSimpleMsg jpushSimpleMsg;
    JpushAudience audience;
    PushJsonMsg pushJsonMsg;

    //Getter与Setter
    public ArrayList<String> getrArrayList() {
        return rArrayList;
    }

    public void setrArrayList(ArrayList<String> rArrayList) {
        this.rArrayList = rArrayList;
    }

    public JpushSimpleMsg getJpushSimpleMsg() {
        return jpushSimpleMsg;
    }

    public void setJpushSimpleMsg(JpushSimpleMsg jpushSimpleMsg) {
        this.jpushSimpleMsg = jpushSimpleMsg;
    }

    public JpushAudience getAudience() {
        return audience;
    }

    public void setAudience(JpushAudience audience) {
        this.audience = audience;
    }

    public void setPushJsonMsg(PushJsonMsg pushJsonMsg) {
        this.pushJsonMsg = pushJsonMsg;
    }

    //构造方法
    public PushJsonMsgFactory(JSONObject obj) {
        rArrayList = getRidFromRequest(obj);
        jpushSimpleMsg = new JpushSimpleMsg();
        jpushSimpleMsg.setMsg_content(getMsgTypeFromRequest(obj));
        jpushSimpleMsg.setExtras(getExtrasFromRequest(obj));
        audience = new JpushAudience();
        audience.setRegistration_id(rArrayList);
        pushJsonMsg = new PushJsonMsg();
        pushJsonMsg.setMessage(jpushSimpleMsg);
        pushJsonMsg.setAudience(audience);
    }


    public String getPushJsonMsg() {

        String result = JSON.toJSONString(pushJsonMsg);
        return result;
    }

    public String getGwInfoPushJsaonMsg() {
        jpushSimpleMsg.setMsg_content(2);
        pushJsonMsg.setMessage(jpushSimpleMsg);
        pushJsonMsg.setAudience(audience);
        String result = JSON.toJSONString(pushJsonMsg);
        return result;
    }


    //获取post过来的json字符串中的rid信息.
    public ArrayList<String> getRidFromRequest(JSONObject obj) {
        JsonMsg jsonMsg = new JsonMsg();
        JSONArray rlist = obj.getJSONArray("registration_id");
        ArrayList<String> rArrayList = new ArrayList<String>();

        for (int i = 0; i < rlist.size(); i++) {
            String rlistStr = rlist.get(i).toString();
            rArrayList.add(rlistStr);
        }
        return rArrayList;
    }


    //获取type值
    public int getMsgTypeFromRequest(JSONObject obj) {

        int message_type = 0;
        try {
            message_type = obj.getInteger("message_type");
        } catch (NullPointerException e) {
            message_type = 0;
        }
        return message_type;
    }

    //将载荷extras键值转成HashMap.
    public HashMap<String, Object> getExtrasFromRequest(JSONObject obj) {

        Set<Map.Entry<String, Object>> m = obj.getJSONObject("extras").entrySet();
        Iterator<Map.Entry<String, Object>> it = m.iterator();
        HashMap<String, Object> extras = new HashMap<>();
        while (it.hasNext()) {
            Map.Entry<String, Object> en = it.next();
            String key = en.getKey();
            Object value = en.getValue();
            extras.put(key, value);
        }
        return extras;
    }
}
