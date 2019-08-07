package com.ldyfortest.web;

import com.restservice.BBSRestApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

/**
 * Created by liudeyu on 2019/8/7.
 */

@RunWith(SpringRunner.class)
@EnableCaching
@SpringBootTest(classes = BBSRestApplication.class)
@TestPropertySource(locations = {"classpath:rest.properties"})
@WebAppConfiguration
//配置事务的回滚,对数据库的增删改都会回滚,便于测试用例的循环利用
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class BaseTest {


    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void beforeTestConfigure() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


}