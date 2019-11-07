package com.cloudhearing.ealbum.service.impl;

import com.cloudhearing.ealbum.entity.RandomCheckCode;
import com.cloudhearing.ealbum.utils.TokenTool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RandomCodeService {
    private List<RandomCheckCode> randomCheckCodeList = new ArrayList<>();

    public List<RandomCheckCode> getRandomCheckCodeList() {
        return randomCheckCodeList;
    }

    public RandomCheckCode createRandomCheckCode(RandomCheckCode randomCheckCode) {
        for (int i = 0; i < randomCheckCodeList.size(); i++) {
            //如果记录存在
            RandomCheckCode rcc = randomCheckCodeList.get(i);
            if (rcc.getSn().equals(randomCheckCode.getSn())) {
                rcc.setCreateTime(new Date());
                //生成一个6位数字的随机码?
                String randomCode = TokenTool.getRandomNumber(6);
                rcc.setRandomCode(randomCode);
                return rcc;
            }
        }
        //如果记录不存在
        randomCheckCode.setCreateTime(new Date());
        String randomCode = TokenTool.getRandomNumber(6);
        randomCheckCode.setRandomCode(randomCode);
        randomCheckCodeList.add(randomCheckCode);
        return randomCheckCode;

    }

    public RandomCheckCode getRandomCheckCodeByCode(String randomCode) {

        for (int i = 0; i < randomCheckCodeList.size(); i++) {

            if (randomCheckCodeList.get(i).getRandomCode().equals(randomCode)) {
                long timeNow = new Date().getTime();
                long timePassed = timeNow - randomCheckCodeList.get(i).getCreateTime().getTime();
                //System.out.println("time passed: " + timePassed);
                if (timePassed < 5 * 60 * 1000) {
                    return randomCheckCodeList.get(i);
                }
            }
        }
        return null;
    }
}

