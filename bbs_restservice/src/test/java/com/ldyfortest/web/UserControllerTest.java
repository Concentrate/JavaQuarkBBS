package com.ldyfortest.web;

import common.entity.User;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liudeyu on 2019/8/7.
 */


public class UserControllerTest extends BaseTest {

    public static final String LOCAL_URL = "localhost:9081";
    public static final String BASE_PREFIX = "/user";

    private static final String xiaopangToken = "-38a6a8c6b6ce03dfe68406422ba284a3";

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
    @Transactional
    @Rollback(value = false)
    public void testAddUser() throws Exception {
        String addUserUrl = "/user";
        mockMvc.perform(MockMvcRequestBuilders.post(addUserUrl).param("email", xiaoPang.getEmail())
                .param("name", xiaoPang.getUsername()).param("password", xiaoPang.getPassword()))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void login() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_PREFIX + "/login")
                .param("email", xiaoPang.getEmail()).param("password", xiaoPang.getPassword())
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


    private void logPrintUserByToken(String token) {
        forTestAndDoPrintJson(() -> {
            MvcResult re = mockMvc.perform(MockMvcRequestBuilders.get(BASE_PREFIX + "/{1}", token))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            return re;
        });
    }


    @Test
    @Rollback(value = false)
    public void modifyInfo() {
        forTestAndDoPrintJson(() -> {
            String token = loginInWithEmail(xiaoPang.getEmail(), xiaoPang.getPassword());
            return mockMvc.perform(MockMvcRequestBuilders.put(BASE_PREFIX + "/{1}", token).param(
                    "signature", "小胖如果变成小瘦"
            ).param("sex", "0")
                    .param("username", "小便便")).andDo(MockMvcResultHandlers.print())
                    .andReturn();
        });
    }


    @Test
    public void logoutTest() {
        forTestAndDoPrintJson(() -> {
            String token = loginInWithEmail(xiaoPang.getEmail(), xiaoPang.getPassword());
            mockMvc.perform(MockMvcRequestBuilders.post(BASE_PREFIX + "/logout").param("token",
                    token)).andDo(MockMvcResultHandlers.print()).andReturn();

            logPrintUserByToken(token);
            return null;
        });
    }


    @Test
    public void testUserMessage() {
        forTestAndDoPrintJson(() -> {
            String token = loginInWithEmail(xiaoPang.getEmail(), xiaoPang.getPassword());
            return mockMvc.perform(MockMvcRequestBuilders.get(BASE_PREFIX + "/message")
                    .param("token", token))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        });
    }


    @Test
    public void changePassword() {
        forTestAndDoPrintJson(() -> {
            String token = loginInWithEmail(xiaoPang.getEmail(), xiaoPang.getPassword());
            return mockMvc.perform(MockMvcRequestBuilders.put(BASE_PREFIX + "/password/{1}", token)
                    .param("oldPass", xiaoPang.getPassword()).param("newPass", "okthatisfine"))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        });
    }

    @Test
    public void getUserNewPosts() {
        forTestAndDoPrintJson(() -> {
            User user = getUserInfoWithEmailPass(xiaoPang.getEmail(), xiaoPang.getPassword());
            return mockMvc.perform(MockMvcRequestBuilders.get(BASE_PREFIX + "/detail/{id}", user.getId())
            ).andDo(MockMvcResultHandlers.print()).andReturn();
        });
    }


}
