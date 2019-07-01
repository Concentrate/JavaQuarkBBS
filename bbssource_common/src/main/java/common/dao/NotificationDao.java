package common.dao;

import common.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by liudeyu on 2019/6/30.
 */
public interface NotificationDao extends JpaRepository<Notification,Integer>,JpaSpecificationExecutor{


}
