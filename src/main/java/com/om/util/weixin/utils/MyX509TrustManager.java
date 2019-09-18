package com.om.util.weixin.utils;


import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;
/**
 * @Author: luohua
 * @Date: 2019/9/12 14:39
 * @Version 1.0
 * 功能模块：证书信任管理器（用于https请求）
 */
public class MyX509TrustManager implements X509TrustManager{

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        // TODO Auto-generated method stub

    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        // TODO Auto-generated method stub

    }

    public X509Certificate[] getAcceptedIssuers() {
        // TODO Auto-generated method stub
        return null;
    }

}
