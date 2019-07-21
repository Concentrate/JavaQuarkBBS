package com.restservice.service.imp;

import com.restservice.service.UserService;
import common.dao.UserDao;
import common.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp extends BaseIntegerKeyServiceImp<UserDao, User> implements UserService {

}
