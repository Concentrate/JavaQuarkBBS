package com.ldyfortest.web;

import common.entity.User;

/**
 * Created by liudeyu on 2019/8/11.
 */
public class TestLoginUserInfo {

    static User xiaoPang() {
        User xiaoPang = new User();
        xiaoPang.setUsername("大胖");
        xiaoPang.setSignature("大胖大胖就是胖");
        xiaoPang.setEmail("w3m@kk.com");
        xiaoPang.setPassword("scienceischangingtheworld");
        return xiaoPang;
    }

}
