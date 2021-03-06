package com.om.controller;

import com.om.service.UserService;
import com.om.service.UserServiceThread;
import com.om.service.UserServiceThread2;
import com.om.service.UserServiceThread3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutionException;

/**
 * @Author: luohua
 * @Date: 2019/3/29 13:59
 * @Version 1.0
 * 功能模块：
 */
@Controller
@RequestMapping("/main")
public class UserController {
    @Autowired
     UserService userService;
    @Autowired
    UserServiceThread userServiceThread;
    @Autowired
    UserServiceThread2 userServiceThread2;
    @Autowired
    UserServiceThread3 userServiceThread3;

    @RequestMapping("/user")
    @ResponseBody
    public String getUserInfo(String userid) throws ExecutionException, InterruptedException {
        System.out.println("开始执行");
        //return userService.getUserInfo(userid);
        //return userServiceThread.getUserInfo(userid);
//        return userServiceThread2.getUserInfo(userid).toString();
       return userServiceThread3.getUserInfo(userid).toString();
    }

}
