package com.restservice.service.imp;

import com.restservice.service.ReplyService;
import common.dao.PostDao;
import common.dao.ReplyDao;
import common.entity.Reply;
import common.exceptions.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by liudeyu on 2019/7/23.
 */

@Service
public class ReplyServiceImp extends BaseIntegerKeyServiceImp<ReplyDao, Reply> implements ReplyService {

    @Autowired
    PostDao postDao;


    private void basePageDectect(int pageNo, int length) {
        if (pageNo <= 0 || length <= 0) {
            throw new ApiException(common.utils.Constants.ERROR_REQUEST_PARA_REASON);
        }
    }

    @Override
    public Page<Reply> getPageReply(int postId, int pageNo, int length) {
        basePageDectect(pageNo, length);
        PageRequest pageRequest = new PageRequest(pageNo, length);
        return repo.findByPostsOrderByInitTimeDesc(postDao.findOne(postId), pageRequest);
    }
}
