package com.atguigu.spzx.common.anno;

import com.atguigu.spzx.common.config.InterceptorConfig;
import com.atguigu.spzx.common.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //作用于类的上面
@Retention(RetentionPolicy.RUNTIME) //运行时该注解生效
@Import({InterceptorConfig.class, TokenInterceptor.class}) //将指定的类导入Spring容器
public @interface EnableTokenInterceptor {
}