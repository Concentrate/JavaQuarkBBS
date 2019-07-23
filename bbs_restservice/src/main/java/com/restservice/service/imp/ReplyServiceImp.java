package com.restservice.service.imp;

import com.restservice.service.ReplyService;
import common.dao.ReplyDao;
import common.entity.Reply;
import org.springframework.stereotype.Service;

/**
 * Created by liudeyu on 2019/7/23.
 */

@Service
public class ReplyServiceImp extends BaseIntegerKeyServiceImp<ReplyDao,Reply> implements ReplyService {

}
