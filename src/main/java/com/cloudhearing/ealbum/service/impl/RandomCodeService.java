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

    public void createRandomCheckCode(RandomCheckCode randomCheckCode) {
        for (int i = 0; i < randomCheckCodeList.size(); i++) {
            //如果记录存在
            if (randomCheckCodeList.get(i).getSn().equals(randomCheckCode.getSn())) {
                randomCheckCodeList.get(i).setCreateTime(new Date());
                randomCheckCodeList.get(i).setRandomCode(randomCheckCode.getRandomCode());
                return;
            }
        }
        //如果记录不存在
        randomCheckCode.setCreateTime(new Date());
        randomCheckCodeList.add(randomCheckCode);

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

