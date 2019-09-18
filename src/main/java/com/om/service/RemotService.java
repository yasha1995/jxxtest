package com.om.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author: luohua
 * @Date: 2019/3/28 9:09
 * @Version 1.0
 * 功能模块：
 */
@Service
@Configuration
public class RemotService {
    @Autowired
     RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    /**
     * 获取用户信息
     * @param userId 用户id
     *
     */
    public String getUserInfo(String userId){
        long currentTimeMillis = System.currentTimeMillis();
//        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
//        requestParam.set("userid", "123");
//        Map result = restTemplate.postForObject("http://127.0.0.1:8083/admins/base/StudentInfoController/getMenus.do", requestParam, Map.class);
        //String result = restTemplate.getForEntity("http://127.0.0.1:8083/admins/base/AuthorityController/getMenus.do?menuType="+userId, String.class).getBody();
        //String result = "{'q':'123'}";
        try {
            Thread.currentThread().sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String,String> map = new HashMap<String,String>();
        map.put("user","123456");
        map.put("card","123456");
        System.out.println("获取用户基本信息时间为:"+ (System.currentTimeMillis()-currentTimeMillis));
        //return result;
//        return (String) result.get("user");
        return "{'q':'w','w','q'}";
    }
    public String getUserMoney(String userId){
        long currentTimeMillis = System.currentTimeMillis();
        String url = "http://127.0.0.1:8080/money?userId="+userId;
         ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
        //ResponseEntity<String> result1 = restTemplate.exchange()
        try {
            Thread.currentThread().sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "-1";
        }
        Map<String,String> map = new HashMap<String,String>();
        map.put("money","10000000000");
        map.put("card","123456");
        System.out.println("获取用户余额信息时间为:"+ (System.currentTimeMillis()-currentTimeMillis));
       // return result.getBody();
        return "{'w':'321'}";
    }
}
