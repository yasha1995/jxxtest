package com.om.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: luohua
 * @Date: 2019/3/29 14:00
 * @Version 1.0
 * 功能模块：串行实现 错误的线程实现（无效果）
 */
@Service
public class UserService {

    @Autowired
     RemotService remotService;

    /**
     *
     * @param userId
     * @return
     */
    public String getUserInfo(String userId){
        long t1 = System.currentTimeMillis();
        String v1 = remotService.getUserInfo(userId);
        JSONObject userInfo = JSONObject.parseObject(v1);//{1,2,3}
        String v2 = remotService.getUserMoney(userId);
        JSONObject moneyInfo = JSONObject.parseObject(v2);//{4,5,6}
        //接口聚合
        JSONObject result = new JSONObject();
        result.putAll(userInfo);
        result.putAll(moneyInfo);//{1,2,3,4,5,6}

        System.out.println("执行的时间为:"+ (System.currentTimeMillis()-t1));
        return result.toString();
    }
}
