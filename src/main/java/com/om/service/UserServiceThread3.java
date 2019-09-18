package com.om.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @Author: luohua
 * @Date: 2019/3/29 13:37
 * @Version 1.0
 * 功能模块：并行线程，总时间略大于用时最长线程的时间，使用线程池 自定义FutureTask(比如EnjoyFutureTask)
 */
@Service
public class UserServiceThread3 {

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
        EnjiyFutureTask<JSONObject> task1 = new EnjiyFutureTask<JSONObject>(queryUserInfo);
        EnjiyFutureTask<JSONObject> task2 = new EnjiyFutureTask<JSONObject>(queryMoneyInfo);
//            new Thread(task1).start();
//            new Thread(task2).start();
        threads.submit(task1);//返回一个future对象，如果想知道线程结果就使用submit提交
                                            //如 Future future=threads.submit(task1);
        threads.submit(task2);
        //threads.execute(task1);//execute不需要返回值，不需要返回值是execute的性能远好于submit

        //如何关闭线程池
        //threads.shutdown();
        //不再接受新的任务，之前提交的任务等执行结束再关闭线程池。
        //threads.shutdownNow();

        //接口聚合
        JSONObject result = new JSONObject();
        result.putAll(task1.get());
        result.putAll(task2.get());//{1,2,3,4,5,6}

        System.out.println("执行的时间为:"+ (System.currentTimeMillis()-t1));
        return result.toString();
    }
}
