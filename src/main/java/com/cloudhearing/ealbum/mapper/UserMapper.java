package com.cloudhearing.ealbum.mapper;

import com.cloudhearing.ealbum.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> listAll();

    User checkLogin(User user);

    int addUser(User user);

    int removeUser(User user);

    int editUser(User user);

}
