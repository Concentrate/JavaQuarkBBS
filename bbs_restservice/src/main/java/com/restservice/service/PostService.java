package com.restservice.service;

import com.restservice.service.imp.BaseIntegerKeyServiceImp;
import common.entity.Label;
import common.entity.Posts;
import common.entity.Reply;
import common.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService extends IntegerKeyBaseService<Posts> {

    void savePosts(Posts posts, User user,int labelId);

    Page<Posts> getPostsByPage(String type,String search,int pageNo,int length);

    List<Posts> getPostsByUser(User user);

    Page<Posts> getPostsByLabel(Label label,int pageNo,int length);

    List<Posts> getPostByUserLimitNum(User user,int limitCount);

    List<Posts> getHotPostByLimitHour(User user,int recentHour);

    List<Posts> getNewPostsLimitNum(int recentHour, int limit);


}
