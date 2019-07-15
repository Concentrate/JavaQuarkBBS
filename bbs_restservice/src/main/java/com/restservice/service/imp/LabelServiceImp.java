package com.restservice.service.imp;

import com.restservice.service.LabelService;
import common.dao.LabelDao;
import common.entity.Label;
import org.springframework.stereotype.Service;

/**
 * Created by liudeyu on 2019/7/15.
 */
@Service
public class LabelServiceImp extends BaseIntegerKeyServiceImp<LabelDao, Label> implements LabelService {
}
