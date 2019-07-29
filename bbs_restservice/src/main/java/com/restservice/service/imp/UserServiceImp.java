package com.restservice.service.imp;

import com.github.wenhao.jpa.Specifications;
import com.restservice.service.RedisService;
import com.restservice.service.UserService;
import com.restservice.service.functionbeans.ICryphotEncode;
import com.restservice.service.util.ServiceConstants;
import com.sun.tools.internal.ws.processor.modeler.annotation.WebServiceConstants;
import common.dao.UserDao;
import common.entity.User;
import common.exceptions.ApiException;
import net.sf.ehcache.util.TimeUtil;
import org.assertj.core.util.DateUtil;
import org.hibernate.jpa.criteria.CriteriaBuilderImpl;
import org.hibernate.jpa.criteria.predicate.BooleanExpressionPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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


    @Override
    public List<User> getNewUsersRecentTime(int hourTime) {
        return repo.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Date> init = root.get("initTime");
                if (init != null) {
                    Date current = new Date();
                    Date beforHour = new Date(current.getTime() - hourTime * ServiceConstants.HOUR_IN_MILLISEC);
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


}
