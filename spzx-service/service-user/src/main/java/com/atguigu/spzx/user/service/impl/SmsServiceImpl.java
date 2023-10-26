package com.atguigu.spzx.user.service.impl;

import com.atguigu.spzx.common.SmsUtil;
import com.atguigu.spzx.user.service.SmsService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public void sendCode(String phone) throws Exception {
        //如果Redis中的验证码还在,则不发短信
        Boolean hasKey = redisTemplate.hasKey("user:code"+phone);
        if (hasKey) {
            return;
        }
        //生成一个四位的随机数
        String code = RandomStringUtils.randomNumeric(4);
        //发送短信验证码
        SmsUtil.sendMsg(phone,code);
        //发送成功后,保存验证码到Redis数据库,有效期5分钟
        redisTemplate.opsForValue().set("user:code"+phone,code,5, TimeUnit.MINUTES);
    }
}
