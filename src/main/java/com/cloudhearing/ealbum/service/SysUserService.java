package com.cloudhearing.ealbum.service;

import com.cloudhearing.ealbum.entity.SysUser;
import com.cloudhearing.ealbum.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SysUserService {

    SysUser checkLogin(SysUser sysUser);

    String getSaltByUsername(SysUser sysUser);
}
