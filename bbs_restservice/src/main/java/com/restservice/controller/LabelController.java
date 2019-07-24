package com.restservice.controller;

import com.restservice.service.LabelService;
import common.baseservice.BaseController;
import common.dto.QuarkResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liudeyu on 2019/7/24.
 */

@Api(value = "label service",description = "get label")
@RequestMapping("/label")
@RestController
public class LabelController extends BaseController {


    @Autowired
    LabelService service;

    @ApiOperation(value = "get all label")
    @GetMapping()
    public QuarkResult getLabels(){
        return process(()->{
            return QuarkResult.ok(service.findAll());
        });
    }


}
