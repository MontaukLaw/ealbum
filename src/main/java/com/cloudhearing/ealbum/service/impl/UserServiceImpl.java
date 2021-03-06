package com.cloudhearing.ealbum.service.impl;

import com.cloudhearing.ealbum.entity.User;
import com.cloudhearing.ealbum.mapper.UserMapper;
import com.cloudhearing.ealbum.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public List<User> checkLogin(User user) {
        return userMapper.checkLogin(user);
    }

    @Override
    public List<User> listAll() {
        return userMapper.listAll();

    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);

    }

    @Override
    public int removeUser(User user) {
        return userMapper.removeUser(user);
    }

    @Override
    public int editUser(User user) {
        return userMapper.editUser(user);
    }

    @Override
    public User getUserDetail(User user) {
        return userMapper.getUserDetail(user);
    }

    @Override
    public String getUserJpushID(String id) {

        return userMapper.getUserJpushID(id);
    }

    @Override
    public User getUserByCell(User user) {
        return userMapper.getUserByCell(user);
    }

    @Override
    public User getUserByEmail(User user) {
        return userMapper.getUserByEmail(user);
    }


    @Override
    public int editUserByEmail(User user) {

        return userMapper.editUserByEmail(user);
    }

    @Override
    public int editUserByCellNumber(User user) {

        return userMapper.editUserByCellNumber(user);
    }

}
