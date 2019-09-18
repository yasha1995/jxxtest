package com.om.test;


import java.sql.SQLException;

import org.junit.Test;

import com.om.util.weixin.utils.DBUtility;
/**
 * @Author: luohua
 * @Date: 2019/9/12 17:00
 * @Version 1.0
 * 功能模块：
 */
public class TestDBUtility {

    /**
     * 方法名：testgetConnection</br>
     * 详述：测试是否连接</br>
     * 开发人员：souvc </br>
     * 创建时间：2015-10-5  </br>
     * @throws SQLException
     * @throws
     */
    @Test
    public void testgetConnection() throws SQLException {
        DBUtility db = new DBUtility();
        System.out.println(db.getConnection());
    }


}