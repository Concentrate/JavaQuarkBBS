package com.ldyfortest.web;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by liudeyu on 2019/8/11.
 */
public class ReplyContTest extends BaseTest {

    private static final String BASE_PRE = "/reply";


    @Test
    public void saveReply() {
        forTestAndDoPrintJson(() -> {
            String token = loginInWithEmail(TestLoginUserInfo.xiaoPang().getEmail(), TestLoginUserInfo.xiaoPang().getPassword());
            return mockMvc.perform(post(BASE_PRE)
                    .param("content", "秒啊")
                    .param("token", token)
                    .param("postId", 3 + ""))
                    .andDo(print()).andReturn();
        });
    }


}
