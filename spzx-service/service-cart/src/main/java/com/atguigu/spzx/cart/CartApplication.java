package com.atguigu.spzx.cart;

import com.atguigu.spzx.common.anno.EnableTokenInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.atguigu.spzx")
@EnableFeignClients("com.atguigu.spzx") //启用OpenFeign远程调用,默认扫描当前的包以及子包,自处需要自定义扫描的包
@EnableTokenInterceptor //启用自定义的Token拦截
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)  //排除数据源的自动配置,不链接MySql
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class,args);
    }
}
