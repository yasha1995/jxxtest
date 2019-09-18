package com.om.entity.output;

import com.om.util.weixin.MessageType;

/**
 * @Author: luohua
 * @Date: 2019/9/12 10:36
 * @Version 1.0
 * 功能模块：图片输出内容
 */
public class ImageOutputMessage extends BaseOutMessage{
    private Image Image;
    public Image getImage() {
        return Image;
    }
    public void setImage(Image image) {
        Image = image;
    }
    @Override
    public String getMsgType() {
        return MessageType.RESP_MESSAGE_TYPE_IMAGE.toString();
    }
}
