package com.quark.common;

import com.quark.common.dao.AdminUserDao;
import com.quark.common.entity.AdminUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * Created by lhr on 17-7-30.
 */
@RunWith(SpringRunner.class)
@EnableCaching//缓存支持
@SpringBootTest
public class CommonApplicationTest {

    @Autowired
    private DataSource dataSource;

//    @Autowired
//    private AdminUserDao adminUserDao;

    @Test
    public void TestDataSource(){
//        System.out.println(dataSource.getClass().getName());
        AdminUser adminUser = new AdminUser();
        adminUser.setPassword("666");
        adminUser.setUsername("lhr");
//        adminUserDao.save(adminUser);
//        System.out.println(dataSource.getClass().getName());
    }
}