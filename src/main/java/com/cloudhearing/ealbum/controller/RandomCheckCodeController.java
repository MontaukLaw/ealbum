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
        JsonMsg jsonMsg = new JsonMsg();
        randomCodeService.createRandomCheckCode(randomCheckCode);
        return jsonMsg;
    }

    @GetMapping("/randomCodes/{code}")
    public JsonMsg getRandomCodeByCheckCode(@PathVariable("code") String checkCode) {
        logger.debug(randomCodeService.getRandomCheckCodeList());
        return feedbackJson(randomCodeService.getRandomCheckCodeByCode(checkCode));
    }
}
