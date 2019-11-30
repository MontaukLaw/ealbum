package com.cloudhearing.ealbum;


import com.cloudhearing.ealbum.entity.Device;
import com.cloudhearing.ealbum.entity.ResourceAddress;
import com.cloudhearing.ealbum.entity.User;
import com.cloudhearing.ealbum.service.DeviceService;
import com.cloudhearing.ealbum.service.ResourceAddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceAddressTest {

    @Resource
    ResourceAddressService resourceService;

    @Test
    public void contextLoads() {
    }


    //@Test
    // 1023测试
    public void listAllTest() {
        System.out.println(resourceService.listAll());
    }

    //@Test
    // 1023测试
    public void addResourceAddressTest() {
        ResourceAddress resourceAddress = new ResourceAddress();
        resourceAddress.setStorageId("ALI");
        resourceAddress.setResourceAddress("/fdafd/fdsaf/dfafdaddff.jpg");
        resourceAddress.setSpace(1000);
        resourceAddress.setType(1);
        resourceAddress.setPreviewPic("/fdsaf/dsf.jpg");
        User uploader = new User();
        uploader.setId("uploadeid");
        Device device = new Device();
        device.setSn("sn");
        resourceAddress.setUploader(uploader);
        resourceAddress.setDevice(device);
        System.out.println(resourceService.addResourceAddress(resourceAddress));
    }

    //@Test
    public void testFindByDeviceID() {
        //System.out.println(resourceService.findAllByDeviceID(1, 1, "842f97fe-dd31-11e9-a9a4-0a774baf5008"));
    }

    //@Test
    public void testFindByDeviceSN() {
        System.out.println(resourceService.findAllByDeviceSN(1, 1, "1231"));
    }

    //@Test
    public void testRemoveResourceAddress() {
        ResourceAddress resourceAddress = new ResourceAddress();

        resourceAddress.setId("7723272c-f2d7-11e9-acfd-00163e023e32");

        System.out.println(resourceService.findAllByDeviceSN(1, 5, "842f97fe-dd31-11e9-a9a4-0a774baf5008"));

        System.out.println(resourceService.removeResourceAddress(resourceAddress));

        System.out.println(resourceService.findAllByDeviceSN(1, 5, "842f97fe-dd31-11e9-a9a4-0a774baf5008"));


    }

    //@Test
    public void testFileterSearch() {
        //System.out.println(resourceService.findAllByDeviceSNWithFilter(1, 5, "F288MLUZ5X", "0dea9cb2-eb2f-11e9-acfd-00163e023e32", 1));
        System.out.println(resourceService.findAllByDeviceSNWithFilter(1, 5, "F288MLUZ5X", "0", 1));

    }

    //@Test
    public void testFindByID(){
        ResourceAddress resourceAddress=new ResourceAddress();
        resourceAddress.setId("222");
        System.out.println(resourceService.findResourceById(resourceAddress));
    }
}
