package com.om.entity.menu;

/**
 * @Author: luohua
 * @Date: 2019/9/18 10:39
 * @Version 1.0
 * 功能模块：菜单项的基类
 */
public class Button {

    private String name;//所有一级菜单、二级菜单都共有一个相同的属性，那就是name

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
