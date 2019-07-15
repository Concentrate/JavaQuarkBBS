package com.restservice.service;

import common.entity.Notification;
import common.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liudeyu on 2019/7/15.
 */


public interface NotificationService extends IntegerKeyBaseService<Notification> {

    long getNotifiCount(int uid);

    List<Notification> findNotificationNotRead(User uid);

    List<Notification> getNotificationAll(User user);

    List<Notification> getNotificationIsReaded(User user);



    void deleteNotificationByUser(int uid);
}
