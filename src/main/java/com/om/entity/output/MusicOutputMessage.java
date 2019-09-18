package com.om.entity.output;

import com.om.util.weixin.MessageType;

/**
 * @Author: luohua
 * @Date: 2019/9/12 10:40
 * @Version 1.0
 * 功能模块：回复音乐消息
 */
public class MusicOutputMessage extends BaseOutMessage {
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }

    @Override
    public String getMsgType() {
        return MessageType.RESP_MESSAGE_TYPE_MUSIC.toString();
    }
}
