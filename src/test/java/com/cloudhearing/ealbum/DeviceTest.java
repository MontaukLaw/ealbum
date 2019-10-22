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
    public void testAddDevice() {
        Device device = new Device();
        device.setFirmwareVersion("1.1");
        device.setModel("model");
        device.setSn("sn12312");
        device.setRegion("somewhere");
        device.setJpushId("jpush");
        System.out.println(deviceService.addDevice(device));
    }

    //@Test
    public void updateDevice() {
        Device device = new Device();
        device.setFirmwareVersion("1.2");
        device.setModel("modelx");
        device.setRegion("anywhere");
        device.setTotalSpace(1000);
        device.setUsedSpace(399);
        device.setEmail("fsa@fdsf.com");
        device.setId("6f386000-e62f-11e9-a9a4-0a774baf5008");
        device.setJpushId("jpushxxx");
        System.out.println(deviceService.updateDevice(device));

    }

    //@Test
    public void testBinding() {
        String userID = "81bb89e0-dd02-11e9-a9a4-0a774baf5008";
        String deviceID = "6f386000-e62f-11e9-a9a4-0a774baf5008";
        System.out.println(deviceService.bindDeviceToUser(userID, deviceID));
    }

    //@Test
    public void testGetDeviceByID() {
        Device device = new Device();

        device.setId("6f386000-e62f-11e9-a9a4-0a774baf5008");

        System.out.println(deviceService.getDeviceByID(device));
    }


    //@Test
    public void testGetDeviceBySN() {
        Device device = new Device();
        device.setSn("3B5NCG2AWK");
        System.out.println(deviceService.getDeviceBySN(device));
    }


}
