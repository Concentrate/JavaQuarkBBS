package com.ldyfortest.web;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by liudeyu on 2019/8/8.
 */
public class PostControllerTest extends BaseTest {

    private static final String BASE_PREFIXX = "/posts";


    @Test
    public void searchPostContent() {
        forTestAndDoPrintJson(() -> {
            return mockMvc.perform(get(BASE_PREFIXX).param("search", "Java"))
                    .andDo(print())
                    .andReturn();
        });

    }

}
