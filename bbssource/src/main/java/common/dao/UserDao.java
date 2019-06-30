package common.dao;

import common.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liudeyu on 2019/6/30.
 */
@Repository
@CacheConfig(cacheNames = "users")
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    User findUserByEmail(String email);

    User findUserByUsername(String name);

    @Query(value = "select * from quark_user u where DATE_SUB(CURDATE(),INTERVAL 3000 DAY)<=DATE(u.init_time) ORDER BY u.id DESC limit 12", nativeQuery = true)
    List<User> findNewUser();
}
