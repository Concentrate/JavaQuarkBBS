package com.quark.rest;

import com.quark.common.dao.AdminUserDao;
import com.quark.common.dao.PostsDao;
import com.quark.rest.service.PostsService;
import com.quark.rest.service.RedisService;
import com.quark.rest.service.ReplyService;
import com.quark.rest.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.PrintingResultHandler;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by lhr on 17-7-31.
 * user.setUsername("lhr");
 * user.setPassword("root");
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"classpath:rest.properties"})
@SpringBootTest
public class RestApplicationTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserService userService;

    @Autowired
    DataSource dataSource;

    @Autowired
    PostsService postsService;

    @Autowired
    ReplyService replyService;

    @Autowired
    AdminUserDao dao;

    @Autowired
    RedisService<Integer> redisService;

    @Autowired
    PostsDao postsDao;

    @Value("${REDIS_USERID_KEY}")
    private String REDIS_USERID_KEY;


    private MockMvc mockMvc;

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    WebApplicationContext context;

    @Before
    public void beforeConfigure() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    @Test
    public void testDataSource() {
//        userService.updateUserPassword("2a8e48c8-9d09-4ef4-892c-b3436070297c","12345678","123456");
//        redisService.cacheSet(REDIS_USERID_KEY,8);
//        redisService.cacheSet(REDIS_USERID_KEY,10);
//        redisService.deleteSet(REDIS_USERID_KEY,8);
//            System.out.println(redisService.setHasValue(REDIS_USERID_KEY,5));
//        postsDao.findAll();
        System.out.println("say hello");
    }


    @Test
    public void testRestHeaders(){
        String userlogin="http://localhost:8081/user/login";
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get(userlogin)
                .param("name","jemmy")
                .param("email","jemmy@oo.com");
        try {
            mockMvc.perform(requestBuilder)
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
