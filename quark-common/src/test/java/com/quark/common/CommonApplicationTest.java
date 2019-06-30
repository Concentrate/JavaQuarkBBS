package com.quark.common;

import com.quark.common.dao.LabelDao;
import com.quark.common.dao.NotificationDao;
import com.quark.common.dao.PostsDao;
import com.quark.common.dao.UserDao;
import com.quark.common.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by lhr on 17-7-30.
 */
@RunWith(SpringRunner.class)
@EnableCaching//缓存支持
@SpringBootTest
public class CommonApplicationTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDao UserDao;

    @Autowired
    private PostsDao postsDao;

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private NotificationDao notificationDao;

    @Test
    public void TestDataSource(){
        List<User>users= UserDao.findNewUser();
        users.forEach(user -> {
            System.out.println(user.getUsername());
        });
    }
}
