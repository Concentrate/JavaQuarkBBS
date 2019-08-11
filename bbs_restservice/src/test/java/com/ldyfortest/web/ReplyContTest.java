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
            return mockMvc.perform(post(BASE_PRE))
                    .andDo(print()).andReturn();
        });

    }
}
