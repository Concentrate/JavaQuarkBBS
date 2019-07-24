package com.restservice.service;

import common.entity.User;

public interface UserService extends IntegerKeyBaseService<User>{

    User getUserByToken(String token);

    User loginName(String name,String password);
    User loginEmail(String email,String password);
}
