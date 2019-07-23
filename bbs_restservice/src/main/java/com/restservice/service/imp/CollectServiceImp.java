package com.restservice.service.imp;

import com.restservice.service.CollectService;
import common.dao.CollectDao;
import common.entity.Collect;
import org.springframework.stereotype.Service;

/**
 * Created by liudeyu on 2019/7/23.
 */

@Service
public class CollectServiceImp extends BaseIntegerKeyServiceImp<CollectDao,Collect> implements CollectService {
}
