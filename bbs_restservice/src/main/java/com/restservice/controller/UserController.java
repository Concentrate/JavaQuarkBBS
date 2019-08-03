package com.restservice.controller;


import com.restservice.service.PostService;
import com.restservice.service.UserService;
import com.restservice.service.util.StringUtils;
import common.baseservice.BaseController;
import common.dto.QuarkResult;
import common.entity.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@Api(description = "get user info")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;


    @PostMapping()
    public QuarkResult addNewUser(String email, String name, String password) {
        return process(() -> {
                    User user = userService.findUserByName(name);
                    if (user != null) {
                        return QuarkResult.error("user exists");
                    }
                    user = userService.findUserByEmail(email);
                    if (user != null) {
                        return QuarkResult.error("user exists");
                    }
                    userService.register(name, password, email);
                    return QuarkResult.ok();
                }
        );
    }


    @PutMapping("/password/{token}")
    public QuarkResult changePasswordByToken(@PathVariable("token") String token, @RequestParam("oldpass") String oldPass, @RequestParam("newPass") String newPass) {
        return process(() -> {
            boolean result = userService.updatePassword(token, oldPass, newPass);
            return QuarkResult.ok(result);
        });
    }


    @GetMapping("/{token}")
    public QuarkResult getUserInfoByToken(@PathVariable("token") String token) {
        return process(() -> {
            return QuarkResult.ok(userService.getUserByToken(token));
        });
    }


    @GetMapping("/detail/{userId}")
    public QuarkResult getUserRecentPosts(@PathVariable("userId") Integer userId) {
        return process(() -> {
            if (userService.findOne(userId) == null) {
                return QuarkResult.error("userId not exists");
            }
            return QuarkResult.ok(postService.getPostByUserLimitNum(userService.findOne(userId), 10));
        });
    }


    @PostMapping("/login")
    public QuarkResult login(String email, String password) {
        return process(() -> {
            User user=userService.loginEmail(email, password);
            if (user!=null) {
                return QuarkResult.ok();
            } else {
                return QuarkResult.error("login failed or you are banned");
            }
        });
    }

    @PostMapping("/logout")
    public QuarkResult logout(String token){
        return process(()->{
            // TODO: 2019/8/3 do on next
            return QuarkResult.ok();
        });
    }







}
