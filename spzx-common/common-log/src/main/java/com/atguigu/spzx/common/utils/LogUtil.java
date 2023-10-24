package com.atguigu.spzx.common.utils;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.AuthContextUtil;
import com.atguigu.spzx.common.annotation.Log;
import com.atguigu.spzx.model.entity.system.SysOperLog;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class LogUtil {

    //操作执行之后调用
    public static void afterHandleLog(Object proceed, SysOperLog sysOperLog, int status, String errorMsg) {
        //设置响应的结果 9
        sysOperLog.setJsonResult(JSON.toJSONString(proceed));
        // 设置状态(0-正常，1-异常) 10
        sysOperLog.setStatus(status);
        // 设置异常信息 11
        sysOperLog.setErrorMsg(errorMsg);
    }

    //操作执行之前调用
    public static void beforeHandleLog(Log log, ProceedingJoinPoint joinPoint, SysOperLog sysOperLog) {
        // 设置操作模块名称 1
        sysOperLog.setTitle(log.title());
        // 设置操作人的类型 2
        sysOperLog.setOperatorType(log.operatorType().name());

        // 获取目标方法信息
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 设置请求的方法路径 3
        sysOperLog.setMethod(methodSignature.toString());

        // 获取请求相关参数
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 设置请求的方式 4
        sysOperLog.setRequestMethod(request.getMethod());
        // 设置请求路径 5
        sysOperLog.setOperUrl(request.getRequestURI());
        // 设置客户端id地址 6
        sysOperLog.setOperIp(LogUtil.getIpAddress(request));

        // 设置请求参数
        String requestMethod = sysOperLog.getRequestMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = Arrays.toString(joinPoint.getArgs());
            // 设置请求中的参数 7
            sysOperLog.setOperParam(params);
        }
        // 设置操作人的姓名 8
        sysOperLog.setOperName(AuthContextUtil.get().getUserName());
    }

    //获取ip地址
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
            if ("0:0:0:0:0:0:0:1".equals(ipAddress)) {
                ipAddress = "127.0.0.1";
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }

}