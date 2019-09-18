package com.om.controller;

import com.om.service.UserService;
import com.om.service.UserServiceThread;
import com.om.service.UserServiceThread2;
import com.om.service.UserServiceThread3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @Author: luohua
 * @Date: 2019/3/29 13:59
 * @Version 1.0
 * 功能模块：异步模式，提高tomcat的吞吐量
 */
@Controller
@RequestMapping("/main2")
public class UserController2 {
    @Autowired
     UserService userService;
    @Autowired
    UserServiceThread userServiceThread;
    @Autowired
    UserServiceThread2 userServiceThread2;
    @Autowired
    UserServiceThread3 userServiceThread3;

    @RequestMapping("/user2")
    @ResponseBody
    public Callable getUserInfo(String userid) throws ExecutionException, InterruptedException {
        System.out.println("主线程开始执行的时间"+System.currentTimeMillis());
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("子线程开始执行的时间"+System.currentTimeMillis());
                String result = userServiceThread3.getUserInfo(userid).toString();
                System.out.println("子线程结束执行的时间"+System.currentTimeMillis());
                return result;
            }
        };
        System.out.println("主线程结束执行的时间"+System.currentTimeMillis());
        return callable;
    }

}
