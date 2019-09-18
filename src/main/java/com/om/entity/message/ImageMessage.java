package com.om.entity.message;

import com.om.util.weixin.MessageType;

/**
 * @Author: luohua
 * @Date: 2019/9/12 10:04
 * @Version 1.0
 * 功能模块：图片消息
 */
public class ImageMessage extends BaseMessage {
    // 图片链接
    private String PicUrl;
    //图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private String MediaId;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    @Override
    public String getMsgType() {
        return MessageType.IMAGE_MESSAGE.toString();
    }

}
