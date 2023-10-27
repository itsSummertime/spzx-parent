package com.atguigu.spzx.common.interceptor;

import com.alibaba.fastjson2.JSON;
import com.atguigu.spzx.common.AuthContextUtil;
import com.atguigu.spzx.model.entity.user.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头中的token
        String token = request.getHeader("token");
        //获取Redis中token对应的用户信息
        String jsonString = redisTemplate.opsForValue().get("user:login:" + token);

        //转为UserInfo对象
        UserInfo userInfo = JSON.parseObject(jsonString, UserInfo.class);
        //存入线程变量
        AuthContextUtil.setUserInfo(userInfo);

        //放行
        return true;
    }
}

