package com.restservice.service.imp;

import com.restservice.service.RedisService;
import com.restservice.service.UserService;
import com.restservice.service.functionbeans.ICryphotEncode;
import common.dao.UserDao;
import common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class UserServiceImp extends BaseIntegerKeyServiceImp<UserDao, User> implements UserService {

    @Autowired
    RedisService<User> userRedisService;

    @Resource(name = "md5_encode")
    ICryphotEncode passEncodeService;

    @Value("REDIS_USER_KEY")
    private String REDIS_USER_BASE;

    @Value("REDIS_USER_TIME")
    private int expireHour;


    private String createTokenByName(String name) {
        return passEncodeService.encrypt(REDIS_USER_BASE + name);
    }

    @Override
    public User getUserByToken(String token) {
        return userRedisService.getDataAndUpdateTime(token, expireHour);
    }


    private User detectUserLogin(String password, User user) {
        if (user == null) {
            return null;
        }
        String tmpPa = StringUtils.isEmpty(password) ? "" : password;
        if (tmpPa.equals(passEncodeService.encrypt(password))) {
            userRedisService.putDataInMap(createTokenByName(user.getUsername()), user, expireHour);
            return user;
        }
        return null;
    }

    @Override
    public User loginName(String name, String password) {
        User user = repo.findUserByUsername(name);
        return detectUserLogin(password, user);
    }

    @Override
    public User loginEmail(String email, String password) {
        User user = repo.findUserByEmail(email);
        return detectUserLogin(password, user);
    }



}
