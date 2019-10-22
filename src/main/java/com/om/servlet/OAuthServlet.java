package com.om.servlet;

import com.om.entity.pojo.SNSUserInfo;
import com.om.entity.pojo.WeixinOauth2Token;
import com.om.util.weixin.ConfigUtil;
import com.om.util.weixin.WebChatConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: luohua
 * @Date: 2019/9/19 10:59
 * @Version 1.0
 * 功能模块：授权后的回调请求处理
 */
public class OAuthServlet extends HttpServlet {
    private static final long serialVersionUID = -1847238807216447030L;

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        // 用户同意授权
        if (!"authdeny".equals(code)) {
            // 获取网页授权access_token
            WeixinOauth2Token weixinOauth2Token = WebChatConfig.getOauth2AccessToken(ConfigUtil.getProperty("wx_appid"), ConfigUtil.getProperty("wx_secret"), code);
            // 网页授权接口访问凭证
            String accessToken = weixinOauth2Token.getAccessToken();
            // 用户标识
            String openId = weixinOauth2Token.getOpenId();
            // 获取用户信息
            SNSUserInfo snsUserInfo = WebChatConfig.getSNSUserInfo(accessToken, openId);

            // 设置要传递的参数
            request.setAttribute("snsUserInfo", snsUserInfo);
            request.setAttribute("state", state);
        }
        // 跳转到index.jsp
        request.getRequestDispatcher("index1.jsp").forward(request, response);
    }
}
