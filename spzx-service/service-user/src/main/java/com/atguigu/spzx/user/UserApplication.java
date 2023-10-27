package com.atguigu.spzx.user;

import com.atguigu.spzx.common.anno.EnableTokenInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.atguigu.spzx")
@EnableTokenInterceptor //启用自定义的Token拦截器
@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
