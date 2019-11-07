package com.cloudhearing.ealbum;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloudhearing.ealbum.entity.JPushContent;
import com.cloudhearing.ealbum.service.impl.JPushService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    //@Test
    public void testGenJpushContent() {
        jPushService.pushBindingSuccessToUser("140fe1da9ecf1630cff");

    }


}
