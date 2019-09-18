package com.om.entity.output;

import com.om.util.weixin.MessageType;

/**
 * @Author: luohua
 * @Date: 2019/9/12 10:34
 * @Version 1.0
 * 功能模块：文本回复消息
 */
public class TextMessage extends BaseOutMessage {
    // 文本消息
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public String getMsgType() {
        return MessageType.RESP_MESSAGE_TYPE_TEXT.toString();
    }
}
