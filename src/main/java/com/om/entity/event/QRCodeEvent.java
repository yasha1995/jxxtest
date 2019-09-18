package com.om.entity.event;

/**
 * @Author: luohua
 * @Date: 2019/9/12 10:15
 * @Version 1.0
 * 功能模块：扫描带参数二维码事件
 */
public class QRCodeEvent extends BaseEvent {
    // 事件KEY值
    private String EventKey;
    // 用于换取二维码图片
    private String Ticket;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }

}
