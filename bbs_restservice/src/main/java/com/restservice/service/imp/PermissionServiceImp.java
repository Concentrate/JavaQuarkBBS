package com.restservice.service.imp;

import com.restservice.service.PermissionService;
import common.dao.PermissionDao;
import common.entity.Permission;
import org.springframework.stereotype.Service;

/**
 * Created by liudeyu on 2019/7/23.
 */
@Service
public class PermissionServiceImp extends BaseIntegerKeyServiceImp<PermissionDao,Permission> implements PermissionService {
}
