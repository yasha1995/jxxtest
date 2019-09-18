package com.om.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;
/**
 * @Author: luohua
 * @Date: 2019/9/12 11:16
 * @Version 1.0
 * 功能模块：判断是否为空工具
 */
public class EmptyUtil {

    private EmptyUtil() {

    }

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof String) {
            if (StringUtils.isEmpty((String) object)) {
                return true;
            }
        } else if (object instanceof List<?>) {
            List<?> list = (List<?>) object;
            if (list == null || list.size() <= 0) {
                return true;
            }
        } else if (object instanceof Object[]) {
            Object[] arr = (Object[]) object;
            if (arr == null || arr.length == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

}

