package com.om.entity.pojo;

/**
 * @Author: luohua
 * @Date: 2019/9/18 10:47
 * @Version 1.0
 * 功能模块：微信通用接口凭证
 */
public class AccessToken {
    // 获取到的凭证
    private String token;
    // 凭证有效时间，单位：秒
    private int expiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
