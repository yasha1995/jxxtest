package com.om.entity.message;

import com.om.entity.message.BaseMessage;
import com.om.util.weixin.MessageType;

/**
 * @Author: luohua
 * @Date: 2019/9/12 10:07
 * @Version 1.0
 * 功能模块：小视频消息
 */
public class ShortVideoInputMessage extends BaseMessage {
    // 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private String MediaId;
    // 视频消息 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
    private String ThumbMediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

    @Override
    public String getMsgType() {
        return MessageType.SHORTVIDEO_MESSAGE.toString();
    }
}
