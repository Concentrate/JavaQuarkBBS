package common.dao;

import common.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liudeyu on 2019/6/30.
 */
@Repository
public interface AdminUserDao extends JpaRepository<AdminUser,Integer> {
}
