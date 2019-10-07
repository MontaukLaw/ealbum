package com.cloudhearing.okhttp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;

//这个类用于访问极光的推送接口
public class ApiAccess {

    private static final String JPUSH_PREFIX = "{\"platform\": \"all\",\"audience\": {\"registration_id\":";
    private static final String MIDDLE_WITH_MSG = " },\"message\": {\"msg_content\": \"" + "CMD:";
    private static final String JPUSH_POSTFIX = "\", \"content_type\": \"text\",\"title\": \"msg\"}}";
    private String apiAddress;
    private String username;
    private String password;

    public String getApiAddress() {
        return apiAddress;
    }

    public void setApiAddress(String apiAddress) {
        this.apiAddress = apiAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ApiAccess(String apiAddress, String username, String password) {
        this.apiAddress = apiAddress;
        this.username = username;
        this.password = password;
    }

    public String sendBindRequest(JSONObject obj) {
        return sendBindRequestMsg(obj);
        //return sendPushWithMsg(obj, "BIND_REQ");
    }

    public String sendBindResult(JSONObject obj, boolean result) {
        if (result) {
            return sendPushWithMsg(obj, "BIND_APPROVED");
        }
        return sendPushWithMsg(obj, "BIND_DENIED");
    }

    public String sendBindResult(JSONObject obj) {
        return sendPushWithMsg(obj, "BIND_RESULT");
    }

    public String sendSyncBind(JSONObject obj) {
        return sendPushWithoutMsg(obj, "SYNC_BIND");
    }

    public String sendSyncDevInfo(JSONObject obj) {
        return sendPushWithoutMsg(obj, "SYNC_DEV_INFO");
    }

    public String sendBroadcastMsg(JSONObject obj) {
        String msg = obj.getString("msg");
        String pushMsg = "{\"platform\": \"all\",\"audience\": \"all\",\"message\":{\"msg_content\": \"CMD:BROADCAST;MSG:" + msg + "\", \"content_type\": \"text\",\"title\": \"msg\"}}";
        //System.out.println(pushMsg);
        return handlePush(pushMsg);
    }

    public String sendBroadcastNotification(JSONObject obj) {
        String alert = obj.getString("alert");
        String pushNoti = "{\"platform\": \"all\", \"audience\" : \"all\",\"notification\" : {\"alert\" : \"" + alert + "\"}}";
        System.out.println(pushNoti);
        return handlePush(pushNoti);
    }

    public String sendPushWithoutMsg(JSONObject obj, String pushCMD) {

        //拿到rlist
        JSONArray rlist = obj.getJSONArray("registration_id");
        String rlistStr = rlist.toString();
        //jsonMsg.setMsg(msg);
        String pushMsg = JPUSH_PREFIX + rlistStr + MIDDLE_WITH_MSG + pushCMD + JPUSH_POSTFIX;

        return handlePush(pushMsg);
    }

    //  String pushMsg = "{\"platform\": \"all\",\"audience\": {\"registration_id\":" + rlistStr + " },\"message\": {\"msg_content\": \"" + "CMD:" + pushCMD + ";MSG:" + msg + "\", \"content_type\": \"text\",\"title\": \"msg\"}}";
    //

    public String handlePush(String pushMsg) {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(username, password))
                .build();
        RequestBody body = RequestBody.create(JSON, pushMsg);

        Request request = new Request.Builder().url(apiAddress).post(body).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            //System.out.println(response.body().string());
            String result = response.body().string();
            System.out.println(result);
            return result;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public String sendBindRequestMsg(JSONObject obj) {
        //拿到rlist
        JSONArray rlist = obj.getJSONArray("registration_id");
        String rlistStr = rlist.toString();

        //拿到留言
        String msg = obj.getString("msg");
        //增加title
        String title = obj.getString("title");
        String pushMsg = "";
        if (title != null && !title.equals("")) {
            pushMsg = JPUSH_PREFIX + rlistStr + " },\"message\": {\"msg_content\": \"" + "CMD:BIND_REQ;MSG:" + msg + ";TITLE:" + title;
        } else {
            pushMsg = JPUSH_PREFIX + rlistStr + MIDDLE_WITH_MSG + msg;
        }

        String userId = obj.getString("requester_user_id");
        if (userId != null && !userId.equals("")) {
            pushMsg=pushMsg+";REQUID:"+userId;
        }

        String deviceId = obj.getString("requester_dev_id");
        if (deviceId != null && !deviceId.equals("")) {
            pushMsg=pushMsg+";REQDID:"+deviceId;
        }

        pushMsg = pushMsg + JPUSH_POSTFIX;
        System.out.println(pushMsg);
        return handlePush(pushMsg);

    }

    public String sendPushWithMsg(JSONObject obj, String pushCMD) {
        //拿到留言
        String msg = obj.getString("msg");

        //拿到rlist
        JSONArray rlist = obj.getJSONArray("registration_id");
        String rlistStr = rlist.toString();
        String pushMsg = "{\"platform\": \"all\",\"audience\": {\"registration_id\":" + rlistStr + " },\"message\": {\"msg_content\": \"" + "CMD:" + pushCMD + ";MSG:" + msg + "\", \"content_type\": \"text\",\"title\": \"msg\"}}";
        System.out.println(pushMsg);

        return handlePush(pushMsg);
    }

    public String sendNotification(String rid, String content) {
//        MediaType JSON = MediaType.get("application/json; charset=utf-8");
//        //String url = serviceConfig.getApiAddress();
//        //System.out.println("url: " + url);
//        //String url = "https://api.jpush.cn/v3/push";
//        //String json = "{ \"platform\": \"all\",\"audience\": { \"registration_id\": [ \"" + rid + "\"] }, \"message\": {\"android\": { \"alert\": \"" + content + "\"}}}";
////        String json = "{ \"platform\": \"all\",\"audience\": { \"registration_id\": [ \"" + rid + "\"] }, \"message\": {\"msg_content\": \"Hi,JPush\",\n" +
////                "        \"content_type\": \"text\"," +
////                "        \"title\": \"msg\"," +
////                "        \"extras\": {" +
////                "            \"a\": \"b\"" +
////                "        }}}}";
//
//        String json = "{\"platform\": \"all\",\"audience\": {\"registration_id\": [\"" + rid + "\"]},\"message\": {\"msg_content\": \"Hi,JPush\",\"content_type\": \"text\",\"title\": \"msg\",\"extras\": {\"key\": \"value\"}}}";
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(new BasicAuthInterceptor(username, password))
//                .build();
//        RequestBody body = RequestBody.create(JSON, json);
//
//        Request request = new Request.Builder().url(apiAddress).post(body).build();
//        Response response = null;
//        try {
//            response = client.newCall(request).execute();
//            //System.out.println(response.body().string());
//            String result = response.body().string();
//            System.out.println(result);
//            return result;
//        } catch (IOException e) {
//            return e.getMessage();
//        }
        //if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        //return response.body().toString();

        return "";
    }


}
