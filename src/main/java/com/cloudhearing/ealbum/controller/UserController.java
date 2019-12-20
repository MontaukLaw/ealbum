package com.cloudhearing.ealbum.controller;


import com.cloudhearing.ealbum.entity.Device;
import com.cloudhearing.ealbum.entity.User;
import com.cloudhearing.ealbum.service.DeviceService;
import com.cloudhearing.ealbum.service.UserService;
import com.cloudhearing.ealbum.service.impl.JPushService;
import com.cloudhearing.ealbum.utils.JsonMsg;
import com.cloudhearing.ealbum.utils.PasswordGenTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @Autowired
    JPushService jPushService;

    @Autowired
    DeviceService deviceService;

    private User encryptPassword(User user) {

        if (user.getPassword() == null) {
            return user;
        }
        if (user.getPassword().equals("")) {
            return user;
        }

        String saltedPassword = PasswordGenTool.genPassword(user.getPassword());
        user.setPassword(saltedPassword);

        return user;
    }

    //
    private JsonMsg handleDuplicateException(User user) {

        String userCell = user.getCellModel();
        String userEmail = user.getEmail();

        //不能添加相同的手机号
        if (userCell != null && !userCell.equals("")) {
            if (userService.getUserByCell(user) != null) {
                return feedbackErrorJson("Duplicate cell phone number.");
            }
        }

        //不能添加相同的邮件地址
        if (userEmail != null && !userEmail.equals("")) {
            if (userService.getUserByEmail(user) != null) {
                return feedbackErrorJson("Duplicate email address.");
            }
        }
        return new JsonMsg();

    }

    @PostMapping("/users/")
    public JsonMsg addUser(User user) {

        JsonMsg jsonMsg = handleDuplicateException(user);

        if (!jsonMsg.isSuccess()) {
            return jsonMsg;

        }

        User u = encryptPassword(user);


        String uuid = UUID.randomUUID().toString();
        u.setId(uuid);
        int result = userService.addUser(u);
        if (result > 0) {
            return feedbackJson(uuid);
        } else {
            return feedbackErrorJson("Add user failed");
        }
    }

    @PostMapping("/users/checkLogin")
    public JsonMsg checkLogin(User user) {
        User u = encryptPassword(user);
        List<User> userList = userService.checkLogin(u);
        if (userList.size() > 1) {
            return feedbackErrorJson("Over 1 result");
        }
        return feedbackJson(userList.get(0));
    }

    @GetMapping("/users/")
    public JsonMsg listAll() {
        JsonMsg jsonMsg = feedbackJson(userService.listAll());
        return jsonMsg;
    }

    @RequestMapping(value = "/users/", method = RequestMethod.DELETE)
    public JsonMsg removeUser(User user) {
        JsonMsg jsonMsg = feedbackJson(userService.removeUser(user));
        return jsonMsg;
    }


    @RequestMapping(value = "/users/password/byEmail", method = RequestMethod.PUT)
    public JsonMsg updateUserPasswordByEmail(User user) {
        User u = encryptPassword(user);
        return feedbackJson(userService.editUserByEmail(u));

    }

    @RequestMapping(value = "/users/password/byCellNumber", method = RequestMethod.PUT)
    public JsonMsg updateUserPasswordByCellNumber(User user) {
        User u = encryptPassword(user);
        return feedbackJson(userService.editUserByCellNumber(u));

    }

    @RequestMapping(value = "/users/", method = RequestMethod.PUT)
    public JsonMsg updateUser(User user) {

        JsonMsg jsonMsg = handleDuplicateException(user);

        if (!jsonMsg.isSuccess()) {
            return jsonMsg;

        }

        User u = encryptPassword(user);

        int result = userService.editUser(u);


        if (result > 0) {
            List<Device> deviceList = userService.getUserDetail(user).getDevices();
            for (int i = 0; i < deviceList.size(); i++) {
                String deviceJpushID = deviceService.getDeviceJpushID(deviceList.get(i).getSn());

                jPushService.pushUserUpdateToDevice(deviceJpushID, user.getId());
            }

        } else {

            return feedbackErrorJson("Update user failed");
        }

        return feedbackJson(result);
    }

    @GetMapping("/user/{id}/")
    public JsonMsg getDevicesByUserId(@PathVariable("id") String id) {
        User user = new User();
        user.setId(id);
        return feedbackJson(userService.getUserDetail(user));
    }


}
