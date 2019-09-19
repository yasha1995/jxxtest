package com.om.util.weixin;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.List;

import com.om.entity.pojo.SNSUserInfo;
import com.om.entity.pojo.WeixinOauth2Token;
import com.om.entity.pojo.WeixinUserInfo;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

//import com.alibaba.fastjson.JSONObject;
import com.om.entity.pojo.AccessToken;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import com.om.entity.menu.Menu;
import com.om.util.weixin.model.Token;
import com.om.util.weixin.utils.MyX509TrustManager;
/**
 * @Author: luohua
 * @Date: 2019/9/12 14:42
 * @Version 1.0
 * 功能模块：
 */
public class WebChatConfig {
    private static Logger log = LoggerFactory.getLogger(WebChatConfig.class);

    // 获取access_token的接口地址（GET） 限2000（次/天）
    public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    // 创建菜单
    public static final String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    // 查询自定义菜单
    public static final String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    // 删除自定义菜单
    public static final String MENU_DELTE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    // 获取jsapi_ticket的接口地址（GET） 限2000（次/天）
    public static final String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
    // 发送模板消息
    public static final String SEND_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    //获取用户的基本信息
    public static final String WeixinUserInfo = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";

    // 微信appid
    public final static String APPID = ConfigUtil.getProperty("wx_appid");
    // 微信wx_secret
    public final static String SECERT = ConfigUtil.getProperty("wx_secret");

     /**
      * 创建菜单
      *
      * @param menu 菜单实例
      * @param accessToken 有效的access_token
      * @return 0表示成功，其他值表示失败
      */
    public static int createMenu(Menu menu, String accessToken) {
        int result = 0;
        // 拼装创建菜单的url
        String url = MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        // 调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);
        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }

        return result;
    }



//    public static void main(String[] args) {
//        System.out.println(getToken(APPID, SECERT));
//    }
public static void main(String args[]) {
    // 获取接口访问凭证
    String accessToken = CommonUtil.getToken("wx4079b3c62a6f3b8b", "d7b25eefde1e43c89b961dbf68c1fbcd").getAccessToken();
    /**
     * 获取用户信息
     */
    WeixinUserInfo user = getUserInfo(accessToken, "oCy_AwJ_fAOwjOmYmSyPUOgQWNO8");//openId:想要查询的用户的微信号（关注公众号加密之后的微信号）
    System.out.println("OpenID：" + user.getOpenId());
    System.out.println("关注状态：" + user.getSubscribe());
    System.out.println("关注时间：" + user.getSubscribeTime());
    System.out.println("昵称：" + user.getNickname());
    System.out.println("性别：" + user.getSex());
    System.out.println("国家：" + user.getCountry());
    System.out.println("省份：" + user.getProvince());
    System.out.println("城市：" + user.getCity());
    System.out.println("语言：" + user.getLanguage());
    System.out.println("头像：" + user.getHeadImgUrl());
}

    /**
     * 获得Token
     *
     * @param appId
     * @param secret
     * @return
     */
    public static String getToken(String appId, String secret) {
        Token accessTocken = getToken(appId, secret, System.currentTimeMillis() / 1000);
        return accessTocken.getAccessToken();
    }



    /**
     * 获取access_token
     *
     * @param appid
     *            凭证
     * @param appsecret
     *            密钥
     * @return
     */
    public static Token getToken(String appid, String appsecret, long currentTime) {
        Token Token = null;
        // 调用接口获取token
        String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            Token = new Token();
            Token.setAccessToken(jsonObject.getString("access_token"));
            // 正常过期时间是7200秒，此处设置3600秒读取一次
            // 一天有获取2000次的限制 ,设置1小时获取一次AccessToken防止超出请求限制
            Token.setExpiresIn(jsonObject.getInt("expires_in") / 2);
            Token.setAddTime(currentTime);
        }
        return Token;
    }
    /**
     * 获取access_token
     *
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static AccessToken getAccessToken(String appid, String appsecret) {
        AccessToken accessToken = null;

        String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return accessToken;
    }
    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl
     *            请求地址
     * @param requestMethod
     *            请求方式（GET、POST）
     * @param outputStr
     *            提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    private static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
//            jsonObject = JSONObject.parseObject(buffer.toString());
             jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            System.out.println("Weixin server connection timed out.");
        } catch (Exception e) {
            System.out.println("https request error:{}" + e.getMessage());
        }
        return jsonObject;
    }

    /**
     * 获取用户信息
     *
     * @param accessToken 接口访问凭证
     * @param openId 用户标识
     * @return WeixinUserInfo
     */
    public static WeixinUserInfo getUserInfo(String accessToken, String openId) {
        WeixinUserInfo weixinUserInfo = null;
        // 拼接请求地址
        //String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
        String requestUrl = WeixinUserInfo.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 获取用户信息
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                weixinUserInfo = new WeixinUserInfo();
                // 用户的标识
                weixinUserInfo.setOpenId(jsonObject.getString("openid"));
                // 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
                weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
                // 用户关注时间
                weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
                // 昵称
                weixinUserInfo.setNickname(jsonObject.getString("nickname"));
                // 用户的性别（1是男性，2是女性，0是未知）
                weixinUserInfo.setSex(jsonObject.getInt("sex"));
                // 用户所在国家
                weixinUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                weixinUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                weixinUserInfo.setCity(jsonObject.getString("city"));
                // 用户的语言，简体中文为zh_CN
                weixinUserInfo.setLanguage(jsonObject.getString("language"));
                // 用户头像
                weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
            } catch (Exception e) {
                if (0 == weixinUserInfo.getSubscribe()) {
                    log.error("用户{}已取消关注", weixinUserInfo.getOpenId());
                } else {
                    int errorCode = jsonObject.getInt("errcode");
                    String errorMsg = jsonObject.getString("errmsg");
                    log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
                }
            }
        }
        return weixinUserInfo;
    }


    /**
     * 获取网页授权凭证
     *
     * @param appId 公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @param code
     * @return WeixinAouth2Token
     */
    public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
        WeixinOauth2Token wat = null;
        // 拼接请求地址

        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);
        // 获取网页授权凭证
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                wat = new WeixinOauth2Token();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInt("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return wat;
    }

    /**
     * 通过网页授权获取用户信息
     *
     * @param accessToken 网页授权接口调用凭证
     * @param openId 用户标识
     * @return SNSUserInfo
     */
    //@SuppressWarings注解  作用：用于抑制编译器产生警告信息。
    //unchecked:告诉编译器忽略 unchecked 警告信息，如使用List，ArrayList等未进行参数化产生的警告信息。
    //deprecation:如果使用了使用@Deprecated注释的方法，编译器将出现警告信息。使用这个注释将警告信息去掉
    @SuppressWarnings( { "deprecation", "unchecked" })
    public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
        SNSUserInfo snsUserInfo = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                snsUserInfo = new SNSUserInfo();
                // 用户的标识
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
                snsUserInfo.setSex(jsonObject.getInt("sex"));
                // 用户所在国家
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                snsUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                // 用户特权信息
                snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
            } catch (Exception e) {
                snsUserInfo = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return snsUserInfo;
    }
}
