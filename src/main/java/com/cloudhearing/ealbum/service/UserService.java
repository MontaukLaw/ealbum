package com.cloudhearing.ealbum.service;

import com.cloudhearing.ealbum.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> listAll();

    List<User> checkLogin(User user);

    int addUser(User user);

    int removeUser(User user);

    int editUser(User user);

    User getUserDetail(User user);

    String getUserJpushID(String id);

    User getUserByCell(User user);

    User getUserByEmail(User user);

    int editUserByEmail(User user);

    int editUserByCellNumber(User user);
}
