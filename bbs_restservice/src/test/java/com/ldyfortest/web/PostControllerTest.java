package com.ldyfortest.web;

import common.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by liudeyu on 2019/8/8.
 */
public class PostControllerTest extends BaseTest {

    private static final String BASE_PREFIXX = "/posts";

    private User xiaoPang;

    @Override
    public void beforeTestConfigure() {
        super.beforeTestConfigure();
        xiaoPang = new User();
        xiaoPang.setUsername("大胖");
        xiaoPang.setSignature("大胖大胖就是胖");
        xiaoPang.setEmail("w3m@kk.com");
        xiaoPang.setPassword("scienceischangingtheworld");
    }

    @Test
    public void searchPostContent() {
        forTestAndDoPrintJson(() -> {
            return mockMvc.perform(get(BASE_PREFIXX).param("search", "Java"))
                    .andDo(print())
                    .andReturn();
        });
    }


    @Test
    @Transactional(rollbackFor = {Exception.class})
    @Rollback(value = false)
    public void testAddPost() {
        forTestAndDoPrintJson(() -> {
            String token = loginInWithEmail(xiaoPang.getEmail(), xiaoPang.getPassword());
            return mockMvc.perform(post(BASE_PREFIXX).param("content", "这个是小胖帖子content")
                    .param("title", "这是小胖帖子title")
                    .param("token",token)).andDo(print())
                    .andReturn();
        });
    }




    @Test
    public void testPostDetail(){
        forTestAndDoPrintJson(()->{
            String token = loginInWithEmail(xiaoPang.getEmail(), xiaoPang.getPassword());
            return mockMvc.perform(get(BASE_PREFIXX+"/detail/{1}",225)
            ).andReturn();
        });
    }






}
