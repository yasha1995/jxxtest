package com.om.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.Callable;
import com.alibaba.fastjson.JSONObject;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: luohua
 * @Date: 2019/3/29 13:37
 * @Version 1.0
 * 功能模块：并行线程，总时间略大于用时最长线程的时间
 */
@Service
public class UserServiceThread {

    @Autowired
     RemotService remotService;

    public String getUserInfo(String userId) throws ExecutionException, InterruptedException {
        long t1 = System.currentTimeMillis();
        Callable<JSONObject>  queryUserInfo = new Callable<JSONObject>(){
            @Override
            public JSONObject call(){
                String v1 = remotService.getUserInfo(userId);
                JSONObject userInfo = JSONObject.parseObject(v1);//{1,2,3}
                return userInfo;
            }
        };
        Callable<JSONObject>  queryMoneyInfo = new Callable<JSONObject>(){
            @Override
            public JSONObject call(){
                String v2 = remotService.getUserMoney(userId);
                JSONObject moneyInfo = JSONObject.parseObject(v2);//{4,5,6}
                return moneyInfo;
            }
        };
        FutureTask<JSONObject> task1 = new FutureTask<>(queryUserInfo);
        FutureTask<JSONObject> task2 = new FutureTask<>(queryMoneyInfo);
            new Thread(task1).start();
            new Thread(task2).start();
        //接口聚合
        com.alibaba.fastjson.JSONObject result = new com.alibaba.fastjson.JSONObject();
        result.putAll(task1.get());
        result.putAll(task2.get());//{1,2,3,4,5,6}

        System.out.println("执行的时间为:"+ (System.currentTimeMillis()-t1));
        return result.toString();
    }
}
