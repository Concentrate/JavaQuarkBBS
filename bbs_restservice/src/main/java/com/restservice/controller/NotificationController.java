package com.restservice.controller;

import com.restservice.service.NotificationService;
import com.restservice.service.UserService;
import common.baseservice.BaseController;
import common.dto.QuarkResult;
import common.exceptions.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liudeyu on 2019/8/6.
 */

@RestController
@RequestMapping("/notification")
public class NotificationController extends BaseController {


    @Autowired
    NotificationService notificationService;
    @Autowired
    UserService userService;


    @GetMapping("/{uid}")
    public QuarkResult getNotiList(@PathVariable(value = "uid") Integer uid) {
        return process(() -> {
            if (userService.findOne(uid) == null) {
                throw new ApiException("uid not exists");
            }
            return QuarkResult.ok(notificationService.findNotReadWithUid(uid));
        });
    }


    @DeleteMapping("/{uid}")
    public QuarkResult deleteNotification(@PathVariable(value = "uid") Integer uid) {
        return process(() -> {
            if (userService.findOne(uid) == null) {
                throw new ApiException("uid not exists");
            }
            notificationService.updateNotificationIsRead(userService.findOne(uid));
            return QuarkResult.ok();
        });
    }


}
