package com.cloudhearing.ealbum.service.impl;

import com.cloudhearing.ealbum.entity.SysUser;
import com.cloudhearing.ealbum.entity.User;
import com.cloudhearing.ealbum.mapper.SysUserMapper;
import com.cloudhearing.ealbum.mapper.UserMapper;
import com.cloudhearing.ealbum.service.SysUserService;
import com.cloudhearing.ealbum.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    SysUserMapper sysUserMapper;

    @Override
    public SysUser checkLogin(SysUser user) {
        return sysUserMapper.checkLogin(user);
    }


    @Override
    public String getSaltByUsername(SysUser sysUser) {
        return sysUserMapper.getSaltByUsername(sysUser);
    }
}
