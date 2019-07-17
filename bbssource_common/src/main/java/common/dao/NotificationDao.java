package common.dao;

import common.entity.Notification;
import common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liudeyu on 2019/6/30.
 */
@Repository
public interface NotificationDao extends JpaRepository<Notification, Integer>, JpaSpecificationExecutor {

    List<Notification> findNotificationsByTouserOrderByInitTimeDesc(User user);

    List<Notification> getNotificationsByTouserAndReadIsFalseOrderByInitTimeDesc(User user);

    List<Notification> getNotificationsByTouserAndReadIsTrueOrderByInitTimeDesc(User user);


    @Query(value = "select count(id) from quark_notification n where n.touser = :uid AND n.is_read=0", nativeQuery = true)
    long getNotificationCount(int uid);


    @Query("update Notification n set n.read=true where n.touser= ?1")
    @Modifying
    void updateNotificationRead(User user);


    @Query(value = "delete from quark_notification n where n.touser=?1",nativeQuery = true)
    void deleteNotiByToUserId(int uid);


}
