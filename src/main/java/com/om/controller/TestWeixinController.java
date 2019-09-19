package com.om.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: luohua
 * @Date: 2019/9/19 17:48
 * @Version 1.0
 * 功能模块：
 */

@Controller
@RequestMapping(value="/fenxiang")
public class TestWeixinController {


    @RequestMapping(value="/fenxiang1")
    public void fenxiang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        // 跳转到index.jsp
        request.getRequestDispatcher("index2.jsp").forward(request, response);
    }
}
