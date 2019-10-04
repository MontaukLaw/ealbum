package com.cloudhearing.ealbum.controller;

import com.cloudhearing.ealbum.entity.SysUser;
import com.cloudhearing.ealbum.service.SysUserService;
import com.cloudhearing.ealbum.utils.JsonMsg;
import com.cloudhearing.ealbum.utils.PasswordGenTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web")
public class SysUserController extends BaseController {
    @Autowired
    SysUserService sysUserService;

    private SysUser encryptPassword(SysUser sysUser) {

        if (sysUser.getPassword() == null) {
            return sysUser;
        }
        if (sysUser.getPassword().equals("")) {
            return sysUser;
        }
        String username = sysUser.getUsername();
        String salt = sysUserService.getSaltByUsername(sysUser);
        String saltedPassword = PasswordGenTool.genPasswordWithUsernameAndSalt(username, salt, sysUser.getPassword());
        sysUser.setPassword(saltedPassword);

        return sysUser;
    }

    @PostMapping("/checkLogin")
    public JsonMsg checkLogin(SysUser sysUser) {
        SysUser su = encryptPassword(sysUser);
        return feedbackJson(sysUserService.checkLogin(su));
    }
}
