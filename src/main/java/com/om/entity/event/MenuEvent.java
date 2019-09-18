package com.om.entity.event;

/**
 * @Author: luohua
 * @Date: 2019/9/12 10:17
 * @Version 1.0
 * 功能模块：自定义菜单事件
 */
public class MenuEvent extends BaseEvent {
    // 事件KEY值，与自定义菜单接口中KEY值对应
    private String EventKey;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

}