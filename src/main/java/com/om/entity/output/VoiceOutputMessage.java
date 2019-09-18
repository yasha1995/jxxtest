package com.om.entity.output;

import com.om.util.weixin.MessageType;

/**
 * @Author: luohua
 * @Date: 2019/9/12 10:37
 * @Version 1.0
 * 功能模块：语音回复消息
 */
public class VoiceOutputMessage extends BaseOutMessage{
    private Voice voice;

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    @Override
    public String getMsgType() {
        return MessageType.RESP_MESSAGE_TYPE_VOICE.toString();
    }
}
