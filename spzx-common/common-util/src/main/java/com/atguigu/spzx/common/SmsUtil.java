package com.atguigu.spzx.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Short Message Service
 * 短消息服务
 */
public class SmsUtil {

    //发送短信验证码
    public static void sendMsg(String phone, String code) throws Exception {
        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String method = "POST";
        String appcode = "57e60ebed9a34d0ca447208841dc7eef"; //你自己的AppCode
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:" + code); //验证码
        bodys.put("template_id", "CST_ptdie100"); //模版id
        bodys.put("phone_number", phone); //手机号

        //执行HTTP请求第三方平台的接口，发送短信
        HttpUtils.doPost(host, path, method, headers, querys, bodys);
    }
}