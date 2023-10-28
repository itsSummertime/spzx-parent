package com.atguigu.spzx.order;

import com.atguigu.spzx.common.anno.EnableFeignInterceptor;
import com.atguigu.spzx.common.anno.EnableTokenInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.atguigu.spzx")
@EnableFeignClients("com.atguigu.spzx")
@EnableTokenInterceptor //启用自定义的Token拦截器
@EnableFeignInterceptor//启用自定义的Feign拦截器
@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}