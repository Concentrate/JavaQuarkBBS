package com.ldyfortest.web;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by liudeyu on 2019/8/11.
 */


public class RankControllerTest extends BaseTest{

    private static final String BASE_PREFIXX = "/rank";

    @Test
    public void testNewUser(){
        forTestAndDoPrintJson(()->{
            return mockMvc.perform(get(BASE_PREFIXX+"/newUsers")).andDo(print())
                    .andReturn();
        });
    }


    @Test
    public void testNewPosts(){
        forTestAndDoPrintJson(()->{
            return mockMvc.perform(get(BASE_PREFIXX+"/topPosts"))
                    .andDo(print()).andReturn();
        });
    }

}
