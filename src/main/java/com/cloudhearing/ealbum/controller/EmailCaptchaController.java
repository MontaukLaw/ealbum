package com.cloudhearing.ealbum.controller;

import com.cloudhearing.ealbum.entity.EmailCaptcha;
import com.cloudhearing.ealbum.service.CaptchaService;
import com.cloudhearing.ealbum.utils.EmailSender;
import com.cloudhearing.ealbum.utils.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmailCaptchaController extends BaseController {

    @Autowired
    CaptchaService captchaService;

    @PostMapping("/emailCaptcha")
    public JsonMsg sendEmailCaptcha(String emailAddress) throws Exception{

        if (captchaService.ifEmailIsNew(emailAddress)) {

            return feedbackErrorJson("Don't use same email address in 1 minute.");

        } else {

            EmailCaptcha emailCaptcha = captchaService.createNewCaptcha(emailAddress);

            EmailSender.sendCaptchaCode(emailCaptcha);

            return feedbackJson("Captcha code sent to " + emailAddress);

        }

    }

    @PostMapping("/emailCaptcha/check")
    public JsonMsg checkCaptcha(EmailCaptcha emailCaptcha) {

        if (captchaService.ifCaptchaOK(emailCaptcha.getEmail(), emailCaptcha.getCaptchaCode())) {

            return feedbackJson("Correct");
        }

        return feedbackErrorJson("Code error");
    }

}
