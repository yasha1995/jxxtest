package com.om.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.om.thread.TokenThread;
import com.om.util.weixin.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @Author: luohua
 * @Date: 2019/9/12 17:42
 * @Version 1.0
 * 功能模块：做一个web实现监听器。实现7200秒内监听Token
 */
public class InitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    public void init() throws ServletException {
        // 获取web.xml中配置的参数
        TokenThread.appid = getInitParameter("wx4079b3c62a6f3b8b");//appid
        TokenThread.appsecret = getInitParameter("d7b25eefde1e43c89b961dbf68c1fbcd");//appsecret

        log.info("weixin api appid:{}", TokenThread.appid);
        log.info("weixin api appsecret:{}", TokenThread.appsecret);

        // 未配置appid、appsecret时给出提示
        if ("".equals(TokenThread.appid) || "".equals(TokenThread.appsecret)) {
            log.error("appid and appsecret configuration error, please check carefully.");
        } else {
            // 启动定时获取access_token的线程
            new Thread(new TokenThread()).start();
        }
    }
}