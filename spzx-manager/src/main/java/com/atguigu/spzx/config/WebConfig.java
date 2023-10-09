package com.atguigu.spzx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    //统一处理跨域请求
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//对所有的接口地址生效
                .allowCredentials(true) //允许携带cookie
                .allowedOriginPatterns("*") //允许所有源
                .allowedHeaders("*") //允许所有请求头
                .allowedMethods("*") //允许所有Get Post Delete...
                .maxAge(3600); //默认值1800秒,预检请求的缓存时间,缓存时间内不会发送同一个预检
    }
}
