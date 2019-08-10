package com.restservice.controller;

import com.restservice.service.LabelService;
import com.restservice.service.PostService;
import com.restservice.service.ReplyService;
import com.restservice.service.UserService;
import com.restservice.service.util.StringUtils;
import common.baseservice.BaseController;
import common.dto.QuarkResult;
import common.entity.Posts;
import common.entity.User;
import common.exceptions.ApiException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/posts")
@RestController
public class
PostsController extends BaseController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    ReplyService replyService;

    @Autowired
    LabelService labelService;


    @GetMapping()
    public QuarkResult getPost(@RequestParam(value = "search", required = false, defaultValue = "") String search,
                               @RequestParam(value = "type", required = false, defaultValue = "") String type,
                               @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                               @RequestParam(value = "length", required = false, defaultValue = "20") int length) {
        return process(() -> {
            String tmpSear = StringUtils.isEmpty(search) ? "" : search;
            String tmpType = StringUtils.isEmpty(type) ? "" : type;
            return QuarkResult.ok(postService.getPostsByPage(tmpType, tmpSear, pageNo, length));
        });
    }


    @ApiOperation("增加posts")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "内容"),
            @ApiImplicitParam(name = "title", value = "title", dataType = "String")
    })
    @PostMapping
    public QuarkResult addPost(Posts posts, String token,@RequestParam(required = false,defaultValue = "-1") Integer labelId) {
        return process(() -> {
            User user = userService.getUserByToken(token);
            if (user == null) {
                throw new ApiException("用户未登录");
            }
            postService.savePosts(posts, user, labelId);
            return QuarkResult.ok();
        });
    }


    @GetMapping("/detail/{postid}")
    public QuarkResult detailPosts(@PathVariable("postid") Integer postId, int pageNo, @RequestParam(value = "length", defaultValue = "20") int length) {
        return process(() -> {
            return QuarkResult.ok(replyService.getPageReply(postId, pageNo, length));
        });
    }

    @GetMapping("/label/{labelid}/")
    public QuarkResult getPagePostByLabel(@PathVariable("labelid") int labelId, int pageNo, @RequestParam(value = "length", defaultValue = "20") int length) {
        return process(() -> {
            return QuarkResult.ok(postService.getPostsByLabel(labelService.findOne(labelId), pageNo, length));
        });
    }


}
