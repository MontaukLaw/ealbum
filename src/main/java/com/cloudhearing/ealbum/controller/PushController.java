package com.cloudhearing.ealbum.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloudhearing.ealbum.config.ServiceConfig;
import com.cloudhearing.okhttp.ApiAccess;
import com.cloudhearing.ealbum.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cloudhearing.ealbum.utils.MsgWords.INPUT_ERROR;

@RestController
@RequestMapping("/jpush")
public class PushController extends BaseController {

    @Autowired
    public ServiceConfig serviceConfig;

    //@PostMapping("/pushmsg/{targetRId}")
    //public JsonMsg jpush(@PathVariable("targetRId") String targetRId, @RequestBody JSONObject obj) {

    @PostMapping("/pushMsg")
    public JsonMsg jpush(@RequestBody JSONObject obj) {
        JsonMsg jsonMsg = new JsonMsg();
        //先解包跟检查错误
        if (JSONTool.checkInputError(obj)) {
            jsonMsg.setSuccess(false);
            jsonMsg.setMsg(INPUT_ERROR);
            return jsonMsg;
        }

        //根据配置文件的App配置信息, 访问对应的极光推送接口.
        ApiAccess apiAccess = new ApiAccess(serviceConfig.getApiAddress(), serviceConfig.getAppKey(), serviceConfig.getSecret());

        PushJsonMsgFactory pushJsonMsgFactory = new PushJsonMsgFactory(obj);

        String msg = pushJsonMsgFactory.getPushJsonMsg();

        //获取推送结果
        String resultStr = apiAccess.handlePush(msg);
        logger.debug("resultStr string is : " + resultStr);

        //对推送结果进行分析
        JpushResponse response = ResponseStringTool.tranString(resultStr);

        //有错误直接报错
        if (response.isIfError()) {
            return PushResultTool.reslutProcess(resultStr);
        }

        //拿到推送返回中的messageID, 通过messageID, 访问极光的结果查询接口, 希望获取推送结果的具体信息.
        apiAccess.setApiAddress(serviceConfig.getResultApiAddress());
        List<String> rids = pushJsonMsgFactory.getAudience().getRegistration_id();
        JpushResultFactory jpushResultFactory = new JpushResultFactory(response, rids);

        msg = jpushResultFactory.getPushJsonMsg();
        logger.debug("msg string is: " + msg);
        //resultStr即推送的结果的status信息.
        resultStr = apiAccess.handlePush(msg);

        logger.debug("result string is: " + resultStr);

        if (resultStr.indexOf("timeout") > 0 || resultStr.indexOf("error") > 0) {
            return PushResultTool.reslutProcess(resultStr);
        }
        JSONObject jsonObj = (JSONObject) JSON.parse(resultStr);
        JSONObject jsonObjRid = jsonObj.getJSONObject(rids.get(0));

        //将推送结果打包成类输出给接口访问者.
        JpushMsgStatus jpushMsgStatus = new JpushMsgStatus();
        jpushMsgStatus.setStatus(jsonObjRid.getString("status"));
        jsonMsg.setMsg(MsgWords.STATUS_JUST_FOR_REF);
        //System.out.println("status: "+status);
        jsonMsg.setObj(jpushMsgStatus);
        return jsonMsg;

    }
}
