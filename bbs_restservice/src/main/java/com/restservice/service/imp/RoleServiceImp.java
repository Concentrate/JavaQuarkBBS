package com.restservice.service.imp;

import com.restservice.service.RoleService;
import common.dao.RoleDao;
import common.entity.Role;
import org.springframework.stereotype.Service;

/**
 * Created by liudeyu on 2019/7/23.
 */
@Service
public class RoleServiceImp extends BaseIntegerKeyServiceImp<RoleDao,Role> implements RoleService{
}
