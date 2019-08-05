package com.restservice.controller;

import com.restservice.service.PostService;
import com.restservice.service.util.StringUtils;
import common.baseservice.BaseController;
import common.dto.QuarkResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/posts")
public class PostsController extends BaseController {

    @Autowired
    PostService postService;

    @GetMapping()
    public QuarkResult getPost(@RequestParam(value = "search", required = false) String search, @RequestParam(value = "type", required = false)
            String type, @RequestParam("pageNo") int pageNo, int length) {
        return process(() -> {
            String tmpSear = StringUtils.isEmpty(search) ? "" : search;
            String tmpType = StringUtils.isEmpty(type) ? "" : type;
           return QuarkResult.ok(postService.getPostsByPage(tmpType, tmpSear, pageNo, length));
        });
    }




}
