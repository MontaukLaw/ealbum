package com.cloudhearing.ealbum.controller;


import com.cloudhearing.ealbum.entity.User;
import com.cloudhearing.ealbum.service.UserService;
import com.cloudhearing.ealbum.utils.JsonMsg;
import com.cloudhearing.ealbum.utils.PasswordGenTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

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

    @PostMapping("/users/")
    public JsonMsg addUser(User user) {
        User u = encryptPassword(user);
        JsonMsg jsonMsg = feedbackJson(userService.addUser(u));
        return jsonMsg;
    }

    @PostMapping("/users/checkLogin")
    public JsonMsg checkLogin(User user) {
        User u = encryptPassword(user);
        JsonMsg jsonMsg = feedbackJson(userService.checkLogin(u));
        return jsonMsg;
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

    @RequestMapping(value = "/users/", method = RequestMethod.PUT)
    public JsonMsg updateUser(User user) {
        User u = encryptPassword(user);
        JsonMsg jsonMsg = feedbackJson(userService.editUser(u));
        return jsonMsg;
    }

}
