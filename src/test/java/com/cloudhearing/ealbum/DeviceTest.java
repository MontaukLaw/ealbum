package com.cloudhearing.ealbum;


import com.cloudhearing.ealbum.entity.Device;
import com.cloudhearing.ealbum.entity.User;
import com.cloudhearing.ealbum.service.DeviceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceTest {

    @Resource
    DeviceService deviceService;

    @Test
    public void contextLoads() {
    }

    //@Test
    public void listAllTest() {
        System.out.println(deviceService.listAll());
    }

    //@Test
    //1023测试过
    public void testAddDevice() {
        Device device = new Device();
        device.setFirmwareVersion("1.1");
        device.setModel("model");
        device.setSn("test10232234");
        device.setRegion("somewhere");
        device.setJpushId("jpush");
        System.out.println(deviceService.addDevice(device));
    }

    //@Test
    //1023测试过
    public void updateDevice() {
        Device device = new Device();
        device.setFirmwareVersion("1.2");
        device.setModel("modelx");
        device.setRegion("anywhere");
        device.setTotalSpace(1000);
        device.setUsedSpace(399);
        device.setEmail("fsa@fdsf.com");
        device.setJpushId("jpushxxx");
        device.setSn("test10232234");
        System.out.println(deviceService.updateDevice(device));

    }

    //@Test
    //1023测试过
    public void testBinding() {
        String userID = "81bb89e0-dd02-11e9-a9a4-0a774baf5008";
        String sn = "test10232234";
        System.out.println(deviceService.bindDeviceToUser(userID, sn));
    }

//    //@Test
//    public void testGetDeviceByID() {
//        Device device = new Device();
//
////        device.setId("6f386000-e62f-11e9-a9a4-0a774baf5008");
//device.setSn("");
//        System.out.println(deviceService.getDeviceByID(device));
//    }


    //@Test
    //1023测试过
    public void testGetDeviceBySN() {
        Device device = new Device();
        device.setSn("test10232234");
        System.out.println(deviceService.getDeviceBySN(device));
    }

    //@Test
    //1026
    public void testRemoveBinding() {
        String userid = "a";
        String sn = "b";
        System.out.println(deviceService.removeBinding("a", "b"));

    }

    //@Test
    public void bindingCheckTest() {

        String userid = "a";
        String sn = "b";
        System.out.println(deviceService.bindingCheck("b96e2dfe-dd11-11e9-a9a4-0a774baf500", "81CLJ0EXQD"));

    }

    //@Test
    public void getDeviceJpushIDTest() {

        System.out.println(deviceService.getDeviceJpushID("2FRQ28Y05U"));
    }

    //@Test
    public void getDeviceByEmail() {
        Device device = new Device();
        device.setEmail("wangwu");
        System.out.println(deviceService.getDeviceByEmail(device));
    }


    @Test
    public void testUpdateDeviceName() {
        Device device = new Device();
        device.setSn("somesn");
        device.setName("mynameismarc");
        System.out.println(deviceService.updateDeviceName(device));

    }
}
