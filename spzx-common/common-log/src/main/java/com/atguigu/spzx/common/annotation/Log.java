package com.atguigu.spzx.common.annotation;

import com.atguigu.spzx.common.enums.OperatorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //该注解用于方法上
@Retention(RetentionPolicy.RUNTIME) //运行阶段可以获取到该注解
public @interface Log {
    public String title() ;								// 模块名称
    public OperatorType operatorType() default OperatorType.MANAGE;	// 操作人类别
}
