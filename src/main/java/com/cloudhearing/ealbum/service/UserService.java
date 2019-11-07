package com.cloudhearing.ealbum.service;

import com.cloudhearing.ealbum.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> listAll();

    User checkLogin(User user);

    int addUser(User user);

    int removeUser(User user);

    int editUser(User user);

    User getUserDetail(User user);

    String getUserJpushID(String id);
}
