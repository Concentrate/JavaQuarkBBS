package com.restservice.controller;

import com.restservice.service.UserService;
import common.baseservice.BaseController;
import common.dto.QuarkResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liudeyu on 2019/7/28.
 */
@RequestMapping("/rank")
@RestController
public class RankController  extends BaseController {

    @Autowired
    UserService userService;



    @GetMapping("/newUsers")
    public QuarkResult getNewUser(){
        return process(()->{

        });
    }


}
