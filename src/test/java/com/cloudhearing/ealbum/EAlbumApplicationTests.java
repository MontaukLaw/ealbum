package com.cloudhearing.ealbum;

import com.cloudhearing.ealbum.entity.User;
import com.cloudhearing.ealbum.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EAlbumApplicationTests {

    @Resource
    UserService userService;

    @Test
    public void contextLoads() {
    }


    //@Test
    //1023测试过
    public void listAllTester() {

        System.out.println(userService.listAll());
    }

    //@Test
    //1023测试过
    public void checkLoginTester() {
        User user = new User();
        user.setEmail("tester2@com");
        user.setPassword("dsfadasfsadfasdfasd");
        System.out.println(userService.checkLogin(user));

    }

    //@Test
    public void addUserTest() {
        User user = new User();
        user.setPassword("xxx");
        user.setEmail("someone@somedomain.com");
        user.setUsername("username");
        user.setCellNumber("1231231");
        user.setCellModel("cellmodel");
        user.setIcon("icon");
        user.setRegion("region");
        user.setJpushId("jpushxxxxx");
        System.out.println(userService.addUser(user));
    }

    //@Test
    public void removeUser() {
        User user = new User();
        user.setId("1647d6a4-dd1e-11e9-a9a4-0a774baf5008");
        System.out.println(userService.removeUser(user));

    }

    //@Test
    public void editUser() {
        User user = new User();
        user.setId("1647d6a4-dd1e-11e9-a9a4-0a774baf5008");
        user.setPassword("xxx2");
        user.setEmail("someone@somedomain.com2");
        user.setUsername("username2");
        user.setCellNumber("12312312");
        user.setCellModel("cellmodel2");
        user.setIcon("icon2");
        user.setRegion("region2");
        user.setJpushId("jpushxabcd");
        System.out.println(userService.editUser(user));

    }

    //@Test
    //1023测试过
    //1103重新测试
    //1106测试
    //1107测试
    public void getUserDetail() {
        User user = new User();
        user.setId("430f115d-eb30-11e9-acfd-00163e023e32");
        //System.out.println(userService.getUserDetail(user));
        System.out.println(userService.getUserDetail(user).getDevices());
    }

    //@Test
    public void testGetUserJpushID() {
        System.out.println(userService.getUserJpushID("0067054a-ecca-11e9-acfd-00163e023e32"));
    }

    //@Test
    public void testGetUserByCell() {
        User user = new User();
        user.setCellNumber("13714614102");
        System.out.println(userService.getUserByCell(user));

        user.setEmail("luoyong@hotmail.com");
        System.out.println(userService.getUserByEmail(user));
    }

}
