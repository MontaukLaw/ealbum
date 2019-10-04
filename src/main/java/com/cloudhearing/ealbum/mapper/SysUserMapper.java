package com.cloudhearing.ealbum.mapper;

import com.cloudhearing.ealbum.entity.SysUser;
import com.cloudhearing.ealbum.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {

    SysUser checkLogin(SysUser sysUser);

    String getSaltByUsername(SysUser sysUser);

}
