package com.om.entity.output;

/**
 * @Author: luohua
 * @Date: 2019/9/12 10:22
 * @Version 1.0
 * 功能模块：回复消息的公共字段类
 */
public abstract class BaseOutMessage {
    // 接收方帐号（收到的OpenID）
    private String ToUserName;
    // 开发者微信号
    private String FromUserName;
    // 消息创建时间 （整型）
    private long CreateTime;

    // 获取消息类型
    public abstract String getMsgType();

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

}