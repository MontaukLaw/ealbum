package com.cloudhearing.ealbum.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.Random;

public class PasswordGenTool {
    public static String genPassword(String originPassword) {

        // 加密方式
        String algorithmName = "md5";

        // 加密轮数
        int hashIterations = 2;

        // 盐固定...
        SimpleHash hash = new SimpleHash(algorithmName, originPassword, "cloudhearing", hashIterations);
        return hash.toString();
    }

    public static String genPasswordWithUsernameAndSalt(String username, String salt, String originPassword) {
        // 加密方式
        String algorithmName = "md5";

        // 加密轮数
        int hashIterations = 3;

        // 盐固定...
        SimpleHash hash = new SimpleHash(algorithmName, originPassword, username + salt, hashIterations);
        return hash.toString();

    }


}
