package com.cloudhearing.ealbum.service;

import com.cloudhearing.ealbum.entity.EmailCaptcha;
import com.cloudhearing.ealbum.utils.TokenTool;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CaptchaService {

    private static final long CAPTCHA_FRESH_MINISECONDS = 60000L;
    private final static long CAPTCHA_EXPIRE_MINISECONDS = 36000000L;
    private final static int CAPTCHA_CODE_LENGTH = 8;

    Map<String, EmailCaptcha> captchaMap = new HashMap<>();

    private void kickAllExpireCaptcha() {

        Iterator iter = captchaMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String email = (String) entry.getKey();
            EmailCaptcha captcha = (EmailCaptcha) entry.getValue();
            if (captcha.getExpireAfter().getTime() < new Date().getTime()) {

                captchaMap.remove(email);
            }
        }
    }

    public boolean ifEmailIsNew(String email) {

        EmailCaptcha captcha = captchaMap.get(email);

        if (captcha == null) {
            return false;
        }

        if (new Date().getTime() - captcha.getApplyTime().getTime() < CAPTCHA_FRESH_MINISECONDS) {
            return true;
        }
        return false;
    }


    public EmailCaptcha createNewCaptcha(String email) {

        kickAllExpireCaptcha();

        if (captchaMap.get(email) != null) {

            //首先, 如果map中已经有这个地址, 就铲掉原来的
            captchaMap.remove(email);

        }

        //生成新的
        EmailCaptcha emailCaptcha = new EmailCaptcha();

        emailCaptcha.setEmail(email);
        emailCaptcha.setApplyTime(new Date());
        emailCaptcha.setExpireAfter(new Date(new Date().getTime() + CAPTCHA_EXPIRE_MINISECONDS));
        emailCaptcha.setCaptchaCode(TokenTool.getRandomUpcaseChars(CAPTCHA_CODE_LENGTH));

        //加进去
        captchaMap.put(email, emailCaptcha);

        return emailCaptcha;


    }

    public boolean ifCaptchaOK(String email, String captcha) {

        kickAllExpireCaptcha();

        if (captchaMap.get(email).getCaptchaCode().equals(captcha)) {
            return true;

        }
        return false;
    }

}
