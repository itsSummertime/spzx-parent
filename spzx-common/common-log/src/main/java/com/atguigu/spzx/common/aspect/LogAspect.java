package com.atguigu.spzx.common.aspect;

import com.atguigu.spzx.common.annotation.Log;
import com.atguigu.spzx.common.service.SysOperLogService;
import com.atguigu.spzx.common.utils.LogUtil;
import com.atguigu.spzx.model.entity.system.SysOperLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 日志切面类
 */
//@Order(1) //优先级，数值越小，优先级越高
@Aspect //声明当前类是切面类
@Component //实例化当前对象，存入Spring容器
public class LogAspect {

    @Autowired
    private SysOperLogService sysOperLogService;

    /**
     * Around 环绕通知
     * *           com.atguigu.spzx.service..   *.*(..)
     * 所有返回类型    service包以及子包              所有类.所有方法(所有参数)
     *
     * @annotation(log) 切点为写了@Log注解的方法
     */
    @Around("@annotation(log)")
    public Object saveLog(ProceedingJoinPoint joinPoint, Log log) {
        //需要添加的日志数据
        SysOperLog sysOperLog = new SysOperLog();
        //设置目标方法执行前的数据
        LogUtil.beforeHandleLog(log, joinPoint, sysOperLog);

        Object proceed = null;
        try {
            //执行目标方法, 返回值就是目标方法的返回值
            proceed = joinPoint.proceed();
        } catch (Throwable e) {
            //设置目标方法执行后的数据 - 执行异常
            LogUtil.afterHandleLog(proceed, sysOperLog, 1, e.getMessage());
            //添加日志到数据库
            sysOperLogService.add(sysOperLog);

            //抛出异常
            throw new RuntimeException(e);
        }

        //设置目标方法执行后的数据 - 执行成功
        LogUtil.afterHandleLog(proceed, sysOperLog, 0, null);
        //添加日志到数据库
        sysOperLogService.add(sysOperLog);

        return proceed;
    }
}


