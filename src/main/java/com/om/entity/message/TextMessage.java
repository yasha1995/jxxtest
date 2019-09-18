package com.om.entity.message;

import com.om.entity.message.BaseMessage;
import com.om.util.weixin.MessageType;

/**
 * @Author: luohua
 * @Date: 2019/9/12 10:01
 * @Version 1.0
 * 功能模块：文本消息
 */
public class TextMessage extends BaseMessage {
    //文本消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public String getMsgType() {
        return MessageType.TEXT_MESSAGE.toString();
    }

}
