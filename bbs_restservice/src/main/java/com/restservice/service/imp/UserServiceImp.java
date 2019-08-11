package com.restservice.service.imp;

import com.github.wenhao.jpa.Specifications;
import com.restservice.service.RedisService;
import com.restservice.service.UserService;
import com.restservice.service.functionbeans.ICryphotEncode;
import com.restservice.service.util.Constants;
import common.dao.UserDao;
import common.entity.User;
import common.exceptions.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

@Service
@PropertySource("classpath:resource.properties")
public class UserServiceImp extends BaseIntegerKeyServiceImp<UserDao, User> implements UserService {

    @Autowired
    RedisService<User> userRedisService;

    @Resource(name = "md5_encode")
    ICryphotEncode passEncodeService;

    @Value("${REDIS_USER_KEY}")
    private String REDIS_USER_BASE;

    @Value("${REDIS_USER_TIME}")
    private String expireHour;


    /**
     * 改名后，或者改邮箱后，cache短时间内失效
     */
    @Deprecated
    private String createTokenByName(String name, String email, String pass) {
        return passEncodeService.encrypt(REDIS_USER_BASE + name + email + pass);
    }

    // 改password 后本来就会失效，正常合理逻辑
    private String createTokenByPassId(String id, String pass) {
        return passEncodeService.encrypt(REDIS_USER_BASE + id + pass);
    }


    @Override
    public User save(User user) {
        User user1 = super.save(user);
        updateRedisUserCache(user);
        return user1;
    }

    public void updateRedisUserCache(User user) {
        userRedisService.putDataInMap(createTokenByPassId(user.getId() + "",
                user.getPassword()), user, Integer.valueOf(expireHour));
    }

    @Override
    public void saveInBatch(Iterable<User> items) {
        super.saveInBatch(items);
        items.forEach(this::updateRedisUserCache);
    }

    @Override
    public User getUserByToken(String token) {
        return userRedisService.getDataAndUpdateTime(token, Integer.valueOf(expireHour));
    }


    private String detectUserLogin(String password, User user) {
        if (user == null || user.getEnable() == 0) {
            return null;
        }
        if (passEncodeService.isPasswordEqual(password, user.getPassword())) {
            String token = createTokenByPassId(user.getId() + ""
                    , user.getPassword());
            userRedisService.putDataInMap(token, user, Integer.valueOf(expireHour));
            return token;
        }
        return "";
    }

    @Override
    public String loginName(String name, String password) {
        User user = repo.findUserByUsername(name);
        return detectUserLogin(password, user);
    }

    @Override
    public String loginEmail(String email, String password) {
        User user = repo.findUserByEmail(email);
        return detectUserLogin(password, user);
    }

    @Override
    public void loginOut(String token) {
        userRedisService.deleteFromMap(token);
    }


    @Override
    public List<User> getNewUsersRecentTime(int hourTime) {
        return repo.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Date> init = root.get("initTime");
                if (init != null) {
                    Date current = new Date();
                    Date beforHour = new Date(current.getTime() - hourTime * Constants.HOUR_IN_MILLISEC);
                    return criteriaBuilder.between(init, beforHour, current);
                }
                return criteriaBuilder.disjunction();
            }
        });
    }

    @Override
    public User register(String name, String password, String email) {
        Specification<User> userSp = Specifications.
                <User>and().eq("username", name).build();
        User tmp = repo.findOne(userSp);
        if (tmp != null) {
            throw new ApiException("user  exists");
        }
        if (repo.findUserByEmail(email) != null) {
            throw new ApiException("user  exists");
        }

        User newOne = new User();
        newOne.setUsername(name);
        newOne.setPassword(passEncodeService.encrypt(password));
        newOne.setEmail(email);
        newOne.setInitTime(new Date());
        repo.save(newOne);
        return newOne;
    }

    @Override
    public User findUserByName(String name) {
        return repo.findUserByUsername(name);
    }

    @Override
    public User findUserByEmail(String email) {
        return repo.findUserByEmail(email);
    }

    @Override
    public boolean updatePassword(String token, String oldPass, String newPass) {
        User theOne = getUserByToken(token);
        if (theOne == null) {
            return false;
        }
        if (!passEncodeService.isPasswordEqual(oldPass, theOne.getPassword())) {
            return false;
        }
        theOne.setPassword(passEncodeService.encrypt(newPass));
        repo.save(theOne);
        return true;
    }


}
