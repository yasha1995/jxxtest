package com.om.entity.output;

import com.om.util.weixin.MessageType;

/**
 * @Author: luohua
 * @Date: 2019/9/12 10:38
 * @Version 1.0
 * 功能模块：回复视频消息
 */
public class VideoOutPutMessage extends BaseOutMessage {
    private Video Video;

    public Video getVideo() {
        return Video;
    }

    public void setVideo(Video video) {
        Video = video;
    }

    @Override
    public String getMsgType() {
        return MessageType.RESP_MESSAGE_TYPE_VIDEO.toString();
    }
}
