package com.restservice.service;

import common.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by liudeyu on 2019/7/23.
 */
public interface ReplyService extends IntegerKeyBaseService<Reply>{

    Page<Reply> getPageReply(int postId,int pageNo,int length);

}
