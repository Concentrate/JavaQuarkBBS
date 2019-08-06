package com.restservice.service.imp;

import com.restservice.service.NotificationService;
import common.dao.NotificationDao;
import common.dao.UserDao;
import common.entity.Notification;
import common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudeyu on 2019/7/15.
 */
@Service
@Transactional
public class NotificationServiceImp extends BaseIntegerKeyServiceImp<NotificationDao, Notification> implements NotificationService {


    @Autowired
    UserDao userDao;

    @Override
    public long getNotifiCount(int uid) {
        return repo.getNotificationCount(uid);
    }

    @Override
    public List<Notification> findNotificationNotRead(User user) {
        return repo.getNotificationsByTouserAndReadIsFalseOrderByInitTimeDesc(user);
    }

    @Override
    public List<Notification> findNotReadWithUid(Integer uid) {
        User user = userDao.findOne(uid);
        if(user!=null){
            return findNotificationNotRead(user);
        }
        return new ArrayList<>();
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
    public void updateNotificationIsRead(User user) {
        repo.updateNotificationRead(user);
    }


    @Override
    public void deleteNotificationByUser(int uid) {
        repo.deleteNotiByToUserId(uid);
    }
}
