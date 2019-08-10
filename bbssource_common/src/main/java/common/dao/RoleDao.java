package common.dao;

import common.entity.Role;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by liudeyu on 2019/7/23.
 */
@Repository
@CacheConfig(cacheNames = "role")
public interface RoleDao extends JpaRepository<Role,Integer> ,JpaSpecificationExecutor{

}
