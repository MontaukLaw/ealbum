package com.cloudhearing.ealbum;


import com.cloudhearing.ealbum.entity.Device;
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
    public void listAllTest() {
        System.out.println(deviceService.listAll());
    }

    @Test
    public void testAddDevice() {
        Device device = new Device();
        device.setFirmwareVersion("1.1");
        device.setModel("model");
        device.setSn("sn12312");
        device.setRegion("somewhere");
        System.out.println(deviceService.addDevice(device));

    }

    @Test
    public void updateDevice() {
        Device device = new Device();
        device.setFirmwareVersion("1.2");
        device.setModel("modelx");
        device.setRegion("anywhere");
        device.setTotalSpace(1000);
        device.setUsedSpace(399);
        device.setEmail("fsa@fdsf.com");
        device.setId("6f386000-e62f-11e9-a9a4-0a774baf5008");
        System.out.println(deviceService.updateDevice(device));

    }

}