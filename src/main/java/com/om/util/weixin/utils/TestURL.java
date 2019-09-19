package com.om.util.weixin.utils;

import com.om.util.weixin.CommonUtil;

/**
 * @Author: luohua
 * @Date: 2019/9/19 11:16
 * @Version 1.0
 * 功能模块：生成URL编码
 */
public class TestURL {
    public static void main(String[] args) {
//        String source = "http://chiyan.duapp.com/oauthServlet";
        String source = "http://127.0.0.1:8080";
//        String source = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirec";
//        String source = "http://127.0.0.1:8083";//http%3a%2f%2f127.0.0.1%3a8083
        //直接访问以下页面
        //https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx4079b3c62a6f3b8b&redirect_uri=http%3a%2f%2f127.0.0.1%3a8083&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect

        System.out.println(CommonUtil.urlEncodeUTF8(source));
    }

}
