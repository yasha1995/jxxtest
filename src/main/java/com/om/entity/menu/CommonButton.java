package com.om.entity.menu;

/**
 * @Author: luohua
 * @Date: 2019/9/18 10:41
 * @Version 1.0
 * 功能模块：子菜单项 :没有子菜单的菜单项，有可能是二级菜单项，也有可能是不含二级菜单的一级菜单。
 */
public class CommonButton extends Button {

    private String type;
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}