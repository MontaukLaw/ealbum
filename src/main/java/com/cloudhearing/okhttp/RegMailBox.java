package com.cloudhearing.okhttp;

import com.alibaba.fastjson.JSONObject;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegMailBox {

    public final static String EALBUM_URI_ADD_RA_URL = "http://118.89.61.144:8911/mail/user/";

    //userid

    public static boolean regMailbox(String mailName) throws Exception {

        FormBody formBody = new FormBody.Builder()
                .add("userid", mailName)//设置参数名称和参数值
                .build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().post(formBody).url(EALBUM_URI_ADD_RA_URL).build();
        Response response = okHttpClient.newCall(request).execute();
        String result = response.body().string();
        JSONObject jsonObject = JSONObject.parseObject(result);
        Boolean ifSuccess = jsonObject.getBoolean("success");
        System.out.println(result);
        String obj = jsonObject.getString("obj");
        if (obj.equals("Done") && ifSuccess) {
            return true;
        }
        return false;

    }
}
