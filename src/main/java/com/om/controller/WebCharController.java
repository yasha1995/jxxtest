package com.om.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.om.service.WebChatService;
import com.om.util.weixin.MessageType;
import com.om.util.weixin.WebChatUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @Author: luohua
 * @Date: 2019/9/11 17:07
 * @Version 1.0
 * 功能模块：微信开发者接入
 */
@Controller
@RequestMapping(value="/weixin")
public class WebCharController {
    // 开发者接入验证 确认请求来自微信服务器
    @RequestMapping(method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //消息来源可靠性验证
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");       // 随机数
        String echostr = request.getParameter("echostr");//成为开发者验证
        //String signature ="yasha1995";//成为开发者验证
        //确认此次GET请求来自微信服务器，原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败
        PrintWriter out = response.getWriter();
        if(WebChatUtil.checkSignature(signature, timestamp, nonce)){
            System.out.println("=======请求校验成功======" + echostr);
            out.print(echostr);
        }
        out.close();
        out = null;
    }
    /**
     * 接收微信消息处理并做分发
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(method=RequestMethod.POST)
    public void post(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO 消息的接收、处理、响应
        //消息来源可靠性验证
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");       // 随机数
        //确认此次GET请求来自微信服务器，原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败
        if (!WebChatUtil.checkSignature(signature, timestamp, nonce)) {
            //消息不可靠，直接返回
            response.getWriter().write("");
            return;
        }
        //用户每次向公众号发送消息、或者产生自定义菜单点击事件时，响应URL将得到推送
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/xml");
            //调用parseXml方法解析请求消息
            Map<String, String> map = MessageType.parseXml(request, response);
            String MsgType = map.get("MsgType");
            String xml = null;//处理输入消息，返回结果的xml
            if(MessageType.REQ_MESSAGE_TYPE_EVENT.equals(MsgType)){
                xml = WebChatService.parseEvent(map);
            }else{
                xml = WebChatService.parseMessage(map);
            }
            //返回封装的xml
            //System.out.println(xml);
            response.getWriter().write(xml);
        } catch (Exception ex) {
            response.getWriter().write("");
        }
    }


}
