package com.ldyfortest.web;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.druid.support.json.JSONUtils;
import common.entity.User;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by liudeyu on 2019/8/7.
 */


public class UserControllerTest extends BaseTest {

    public static final String LOCAL_URL = "localhost:9081";
    public static final String BASE_PREFIX = "/user";

    private static final String xiaopangToken = "-38a6a8c6b6ce03dfe68406422ba284a3";

    private User newAddUser;

    @Override
    public void beforeTestConfigure() {
        super.beforeTestConfigure();
        newAddUser = new User();
        newAddUser.setUsername("大胖");
        newAddUser.setSignature("大胖大胖就是胖");
        newAddUser.setEmail("w3m@kk.com");
        newAddUser.setPassword("scienceischangingtheworld");

    }

    @Test
    public void testAddUser() throws Exception {
        String addUserUrl = "/user";
        mockMvc.perform(MockMvcRequestBuilders.post(addUserUrl).param("email", newAddUser.getEmail())
                .param("name", newAddUser.getUsername()).param("password", newAddUser.getPassword()))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void login() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_PREFIX + "/login")
                .param("email", newAddUser.getEmail()).param("password", newAddUser.getPassword())
        ).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void getUserByToken() throws Exception {
        MvcResult re = mockMvc.perform(MockMvcRequestBuilders.get(BASE_PREFIX + "/{1}", xiaopangToken))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String body = re.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(body);
        logger.info(jsonObject.toString(4));

    }


}
