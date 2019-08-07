package com.restservice.service;

import common.entity.User;

import java.util.List;

public interface UserService extends IntegerKeyBaseService<User>{

    User getUserByToken(String token);

    String loginName(String name,String password);
    String loginEmail(String email,String password);

    void loginOut(String token);

    List<User> getNewUsersRecentTime(int hourTime);

    User register(String name,String password,String email);

    User findUserByName(String name);
    User findUserByEmail(String email);

    boolean updatePassword(String token,String oldPass,String newPass);


}
