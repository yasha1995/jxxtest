package com.om.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @Author: luohua
 * @Date: 2019/3/29 13:37
 * @Version 1.0
 * 功能模块：并行线程，总时间略大于用时最长线程的时间，使用线程池
 */
@Service
public class UserServiceThread2 {

    @Autowired
     RemotService remotService;

    //线程池
    static ExecutorService threads = Executors.newFixedThreadPool(10);

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
        FutureTask<JSONObject> task1 = new FutureTask<JSONObject>(queryUserInfo);
        FutureTask<JSONObject> task2 = new FutureTask<JSONObject>(queryMoneyInfo);
//            new Thread(task1).start();
//            new Thread(task2).start();
        threads.submit(task1);
        threads.submit(task2);
        //接口聚合
        JSONObject result = new JSONObject();
        result.putAll(task1.get());
        result.putAll(task2.get());//{1,2,3,4,5,6}

        System.out.println("执行的时间为:"+ (System.currentTimeMillis()-t1));
        return result.toString();
    }
}
