package com.restservice.controller;

import com.restservice.service.PostService;
import com.restservice.service.UserService;
import com.restservice.service.util.Constants;
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
public class RankController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;


    @GetMapping("/newUsers")
    public QuarkResult getNewUser() {
        return process(() -> {
            return QuarkResult.ok(userService.getNewUsersRecentTime(Constants.NEW_USER_RECENT_HOUR));
        });
    }

    @GetMapping("/topPosts")
    public QuarkResult getTopPosts() {
        return process(() -> {
            return QuarkResult.ok(postService.getNewPostsLimitNum(Constants.NEW_POSTS_HOUR, Constants.USER_RECENT_POSTS_LIMIT));
        });
    }


}
