package com.cloudhearing.ealbum.controller;

import com.cloudhearing.ealbum.entity.RandomCheckCode;
import com.cloudhearing.ealbum.service.impl.RandomCodeService;
import com.cloudhearing.ealbum.utils.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RandomCheckCodeController extends BaseController {

    @Autowired
    RandomCodeService randomCodeService;


    @PostMapping("/randomCodes/")
    public JsonMsg createRandomCode(RandomCheckCode randomCheckCode) {
        return feedbackJson(randomCodeService.createRandomCheckCode(randomCheckCode));
    }

    @GetMapping("/randomCodes/{code}")
    public JsonMsg getRandomCodeByCheckCode(@PathVariable("code") String checkCode) {
        JsonMsg jsonMsg=new JsonMsg();
        //logger.debug(randomCodeService.getRandomCheckCodeList());
        RandomCheckCode rcc = randomCodeService.getRandomCheckCodeByCode(checkCode);
        if (rcc != null) {
            jsonMsg.setObj(rcc);
            return jsonMsg;
        }
        jsonMsg.setSuccess(false);
        return jsonMsg;
    }
}
