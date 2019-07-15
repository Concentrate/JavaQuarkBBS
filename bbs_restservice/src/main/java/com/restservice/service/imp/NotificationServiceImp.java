package com.restservice.service.imp;

import com.restservice.service.NotificationService;
import common.dao.NotificationDao;
import common.entity.Notification;
import common.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liudeyu on 2019/7/15.
 */
@Service
@Transactional
public class NotificationServiceImp extends BaseIntegerKeyServiceImp<NotificationDao,Notification> implements NotificationService {
    @Override
    public long getNotifiCount(int uid) {
        return repo.getNotificationCount(uid);
    }

    @Override
    public List<Notification> findNotificationNotRead(User user) {
        return repo.getNotificationsByTouserAndReadIsFalseOrderByInitTimeDesc(user);
    }

    @Override
    public List<Notification> getNotificationAll(User user) {
        return repo.findNotificationsByTouserOrderByInitTimeDesc(user);
    }

    @Override
    public List<Notification> getNotificationIsReaded(User user) {
        return repo.getNotificationsByTouserAndReadIsTrueOrderByInitTimeDesc(user);
    }



    @Override
    public void deleteNotificationByUser(int uid) {
        repo.deleteNotiByToUserId(uid);
    }
}
