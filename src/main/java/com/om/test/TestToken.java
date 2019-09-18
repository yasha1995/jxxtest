package com.om.test;

import com.om.entity.Token;
import com.om.util.weixin.CommonUtil;
import com.om.util.weixin.utils.TokenUtil;
import org.junit.Test;

import java.util.Map;

/**
 * @Author: luohua
 * @Date: 2019/9/12 17:15
 * @Version 1.0
 * 功能模块：
 */
public class TestToken {
    @Test
    public void testGetToken3() {
        Map<String, Object> token= TokenUtil.getToken();
        System.out.println(token.get("access_token"));
        System.out.println(token.get("expires_in"));
    }


    @Test
    public void testSaveToken4() {
        Token token= CommonUtil.getToken("wx4079b3c62a6f3b8b", "d7b25eefde1e43c89b961dbf68c1fbcd");
        TokenUtil.saveToken(token);
    }
}
