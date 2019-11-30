package com.cloudhearing.ealbum;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloudhearing.ealbum.entity.JPushContent;
import com.cloudhearing.ealbum.service.impl.JPushService;
import com.cloudhearing.ealbum.utils.DeviceJpusher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPushTest {

    @Autowired
    JPushService jPushService;

    @Test
    public void contextLoads() {
    }

    @Resource
    DeviceJpusher deviceJpusher;

    //@Test
    public void testGenJpushContent() {
        jPushService.pushBindingSuccessToUser("140fe1da9ecf1630cff", "xx");

    }

    //@Test
    public void testPush() {
        //jPushService.pushUserUpdateToDevice();

    }

    //@Test
    public void testSerivceCofnig() {
        JSONObject jsonObject = new JSONObject();
        //DeviceJpusher deviceJpusher = new DeviceJpusher(jsonObject);
        deviceJpusher.init(jsonObject);
    }

    //@Test
    public void transNumberTest(){
        int source = -1893907416;
        String hex = Integer.toHexString(source);
        System.out.println("hex:"+hex);

    }

}
