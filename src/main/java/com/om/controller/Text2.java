package com.om.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author: luohua
 * @Date: 2019/4/1 11:37
 * @Version 1.0
 * 功能模块：
 */
@Controller
public class Text2 {

   // private Logger logger = (Logger) Logger.getLogger(String.valueOf(getClass()));
    private Logger logger1 =  LoggerFactory.getLogger(Text2.class);
    @RequestMapping("/user")

    public Object getUserInfo(@RequestParam(required = false) Map map1){
        //logger.info("接口"+userId);
        logger1.info("接口"+map1.get("userid"));
        try {
            Thread.currentThread().sleep(1100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String,String> map = new HashMap<String,String>();
        map.put("user","123456");
        map.put("card","123456");
        return map;
    }
    @RequestMapping("/money")

    public Object getMoneyInfo(String userId){
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "-1";
        }
        Map<String,String> map = new HashMap<String,String>();
        map.put("money","10000000000");
        map.put("card","123456");
        return map;
    }
}
