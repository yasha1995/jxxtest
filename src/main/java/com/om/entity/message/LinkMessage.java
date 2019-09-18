package com.om.entity.message;

import com.om.entity.message.BaseMessage;
import com.om.util.weixin.MessageType;

/**
 * @Author: luohua
 * @Date: 2019/9/12 10:08
 * @Version 1.0
 * 功能模块：链接消息
 */
public class LinkMessage extends BaseMessage {
    // 消息标题
    private String Title;
    // 消息描述
    private String Description;
    // 消息链接
    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    @Override
    public String getMsgType() {
        return MessageType.LINK_MESSAGE.toString();
    }

}