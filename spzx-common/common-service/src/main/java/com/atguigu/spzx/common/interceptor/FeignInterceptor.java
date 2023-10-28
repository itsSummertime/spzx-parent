package com.atguigu.spzx.common.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //拦截OpenFeign发送的请求，在请求头中添加token >>>>>>>>>>>>>>>>>>>>

        //获取请求属性
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //从请求属性中获取请求对象
        HttpServletRequest request = attributes.getRequest();
        //从请求对象的请求头中获取token
        String token = request.getHeader("token");

        //添加token到OpenFeign的请求头中
        requestTemplate.header("token", token);

    }
}
