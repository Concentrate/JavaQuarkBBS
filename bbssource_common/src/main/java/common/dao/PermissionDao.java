package common.dao;

import common.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liudeyu on 2019/6/30.
 */
public interface PermissionDao extends JpaRepository<Permission,Integer> {
}
