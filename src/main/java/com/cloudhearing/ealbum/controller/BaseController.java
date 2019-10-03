package com.cloudhearing.ealbum.controller;

import com.cloudhearing.ealbum.utils.JsonMsg;
import org.apache.log4j.Logger;

import java.util.List;

public class BaseController {

    private static final long serialVersionUID = 6357869213649815390L;

    protected Logger logger = Logger.getLogger(this.getClass());

    protected JsonMsg feedbackJson(Object object) {
        JsonMsg jsonMsg = new JsonMsg();
        if (object != null) {
            jsonMsg.setObj(object);
            jsonMsg.setMsg("OK");
        } else {
            jsonMsg.setSuccess(false);
            jsonMsg.setMsg("Error");
        }
        return jsonMsg;
    }

    protected JsonMsg feedbackJson(List<?> ObjList) {
        JsonMsg jsonMsg = new JsonMsg();
        int resultNumber = ObjList.size();
        if (resultNumber > 0) {
            jsonMsg.setObj(ObjList);
            jsonMsg.setMsg("OK");
        } else {
            jsonMsg.setSuccess(false);
            jsonMsg.setMsg("Error");
        }
        return jsonMsg;
    }

}
