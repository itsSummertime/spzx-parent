package com.atguigu.spzx;

import com.atguigu.spzx.properties.AuthProperties;
import com.atguigu.spzx.properties.MinioProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableScheduling //启用定时任务
@EnableConfigurationProperties({AuthProperties.class, MinioProperties.class})//启用属性类(将指定的属性类添加到spring容器中)
@EnableTransactionManagement //启用事务管理器
@SpringBootApplication
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class,args);
    }
}
