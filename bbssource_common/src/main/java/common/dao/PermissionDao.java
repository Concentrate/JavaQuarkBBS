package common.dao;

import common.entity.Permission;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liudeyu on 2019/6/30.
 */
@Repository
@CacheConfig(cacheNames = "permission")
public interface PermissionDao extends JpaRepository<Permission,Integer> {
}
