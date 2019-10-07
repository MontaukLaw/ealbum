package com.cloudhearing.ealbum;


import com.cloudhearing.ealbum.entity.SysUser;
import com.cloudhearing.ealbum.service.SysUserService;
import com.cloudhearing.ealbum.utils.PasswordGenTool;
import com.cloudhearing.ealbum.utils.StringTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserTest {

    @Resource
    SysUserService SysUserService;

    @Test
    public void testGenPasswd() {
        System.out.println(PasswordGenTool.genPasswordWithUsernameAndSalt("operator", "bbaf1937-e6b6-11e9-a9a4-0a774baf5008", "1qaz@WSX0okm"));
    }

    @Test
    public void testCheckLogin() {

        SysUser sysUser = new SysUser();
        sysUser.setUsername("operator");
        sysUser.setPassword("f4c8fa5764848eb3ccc6a9164ee28c7f");
        System.out.println(SysUserService.checkLogin(sysUser));
    }

    @Test
    public void testGetSalt() {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("operator");

        System.out.println(SysUserService.getSaltByUsername(sysUser));

    }

    @Test
    public void testStr() {
        //StringTool.getSNTail(,);
        int inputIndex = 221;
        int inputTotal = 5500;
        String totalStr = String.valueOf(inputTotal);
        int length = totalStr.length();
        System.out.println(length);
        String indexStr = String.valueOf(inputIndex);
        int strLength = indexStr.length();
        String temp = "";
        if (strLength < length) {
            int zeroPatch = (length - strLength);
            for (int i = 0; i < zeroPatch; i++) {
                temp = temp + "0";
            }
        }
        temp = temp + indexStr;
        System.out.println(temp);
    }


}
