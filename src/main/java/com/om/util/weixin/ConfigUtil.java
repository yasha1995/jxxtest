package com.om.util.weixin;


import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
/**
 * @Author: luohua
 * @Date: 2019/9/12 14:40
 * @Version 1.0
 * 功能模块： 读取配置文件工具类
 */
public class ConfigUtil {

    private static final Logger LOG = Logger.getLogger(ConfigUtil.class);

    private static Properties config = null;

    /**
     * 返回weixin.properties配置信息
     * @param key key值
     * @return value值
     */
    public static String getProperty(String key) {
        if (config == null) {
            synchronized (ConfigUtil.class) {
                if (null == config) {
                    try {
                        Resource resource = new ClassPathResource("static/weixin/weixin.properties");
                        config = PropertiesLoaderUtils.loadProperties(resource);
                    } catch (IOException e) {
                        LOG.error(e.getMessage(), e);
                    }
                }
            }
        }

        return config.getProperty(key);
    }
}
