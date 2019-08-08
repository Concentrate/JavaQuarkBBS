package com.restservice.controller;


import com.restservice.service.NotificationService;
import com.restservice.service.PostService;
import com.restservice.service.UserService;
import com.restservice.service.util.StringUtils;
import common.baseservice.BaseController;
import common.dto.QuarkResult;
import common.entity.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@Api(description = "get user info")
@RestController
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    NotificationService notificationService;


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
    public QuarkResult changePasswordByToken(@PathVariable("token") String token, @RequestParam("oldPass") String oldPass, @RequestParam("newPass") String newPass) {
        return process(() -> {
            boolean result = userService.updatePassword(token, oldPass, newPass);
            return QuarkResult.ok(result);
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
            String token = userService.loginEmail(email, password);
            if (!StringUtils.isEmpty(token)) {
                return QuarkResult.ok(token);
            } else {
                return QuarkResult.error("login failed or you are banned");
            }
        });
    }

    @PostMapping("/logout")
    public QuarkResult logout(String token) {
        return process(() -> {
            userService.loginOut(token);
            return QuarkResult.ok();
        });
    }

    @GetMapping("/message")
    public QuarkResult getUserMessageByToken(String token) {
        return process(() -> {
            User user = userService.getUserByToken(token);
            if (user == null) {
                return QuarkResult.error("invailed token");
            }
            return QuarkResult.ok(notificationService.getNotifiCount(user.getId()));
        });
    }


    @GetMapping("/{token}")
    public QuarkResult getUserInfoByToken(@PathVariable("token") String token) {
        return process(() -> {
            User user = userService.getUserByToken(token);
            if (user == null) {
                return QuarkResult.error("invailed token");
            }
            return QuarkResult.ok(user);
        });
    }

    @PutMapping("/{token}")
    public QuarkResult modiferUserInfo(@PathVariable("token") String token, @RequestParam(required = false, defaultValue = "") String username,
                                       @RequestParam(required = false, defaultValue = "") String signature,
                                       @RequestParam(value = "sex", required = false, defaultValue = "") String sex) {
        return process(() -> {
            User user = userService.getUserByToken(token);
            if (user == null) {
                return QuarkResult.error("invailed token");
            }
            if (!org.springframework.util.StringUtils.isEmpty(username)) {
                user.setUsername(username);
            }
            if (!org.springframework.util.StringUtils.isEmpty(signature)) {
                user.setSignature(
                        signature
                );
            }
            if (!org.springframework.util.StringUtils.isEmpty(sex)) {
                int res = StringUtils.parseStringToNum(sex);
                if (StringUtils.parseStringInValuedCollection(sex, 0, 1)) {
                    user.setSex(Integer.valueOf(res));
                }
            }
            userService.save(user);

            return QuarkResult.ok(userService.getUserByToken(token));
        });
    }


}
