package com.restservice.controller;

import com.restservice.service.UserService;
import com.restservice.service.util.FileUtils;
import common.baseservice.BaseController;
import common.dto.QuarkResult;
import common.dto.UploadResult;
import common.entity.User;
import common.exceptions.ApiException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by liudeyu on 2019/8/6.
 */
@RestController
@RequestMapping("/upload")
public class UploadController extends BaseController {

    @Autowired
    UserService userService;


    @PostMapping("/image")
    public UploadResult uploadImage(@RequestParam("file") MultipartFile file) {
        UploadResult result = new UploadResult();
        if (file == null || file.isEmpty()) {
            result.setErrorMessage("file is empty");
            return result;
        }
        try {
            String url = FileUtils.uploadFile(file);
            result.setData(new UploadResult.UploadData("url", url));
            return result;

        } catch (IOException ioex) {
            result.setErrorMessage("system error");
        }
        return result;
    }


    @PostMapping("/usericon/{token}")
    public QuarkResult uploadUserIcon(@PathVariable("token") String token, MultipartFile file) {

        return process(() -> {
            if (file == null || file.isEmpty()) {
                throw new ApiException(("file is empty"));
            }

            User user = userService.getUserByToken(token);
            if (user == null) {
                throw new ApiException("user not exists");
            }
            String imageUri = FileUtils.uploadFile(file);
            user.setIcon(imageUri);
            userService.save(user);
            return QuarkResult.ok();
        });


    }


}
