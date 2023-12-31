package com.atguigu.spzx.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@EnableCaching //启用SpringCache缓存功能
@ComponentScan("com.atguigu.spzx")//扫描指定包的内容,SpringBoot的默认扫包机制会失效
@SpringBootApplication
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}