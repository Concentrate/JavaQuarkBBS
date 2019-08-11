package com.restservice.controller;

import com.restservice.service.PostService;
import com.restservice.service.ReplyService;
import com.restservice.service.UserService;
import common.baseservice.BaseController;
import common.dto.QuarkResult;
import common.entity.Reply;
import common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by liudeyu on 2019/7/24.
 */

@RequestMapping("/reply")
@RestController
public class ReplyController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    ReplyService replyService;

    @Autowired
    PostService postService;


    @PostMapping
    public QuarkResult saveReply(Reply reply, String token, Integer postId) {
        return process(() -> {
            if (StringUtils.isEmpty(token)) {
                return QuarkResult.errorApi("please login first");
            }
            User user = userService.getUserByToken(token);
            if (user == null) {
                return QuarkResult.errorApi("token errorApi or expire,login again");
            }
            if (postService.findOne(postId) == null) {
                return QuarkResult.errorApi("post not exists");
            }
            reply.setPosts(postService.findOne(postId));
            Reply data = replyService.save(reply);
            return QuarkResult.ok(data);
        });
    }


}
