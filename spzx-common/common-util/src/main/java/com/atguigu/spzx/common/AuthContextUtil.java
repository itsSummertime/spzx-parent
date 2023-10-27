package com.atguigu.spzx.common;

import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.entity.user.UserInfo;

/**
 * 线程变量的工具类
 */
public class AuthContextUtil {

    // ThreadLocal对象-后台用户
    private static final ThreadLocal<SysUser> threadLocal = new ThreadLocal<>() ;
    // ThreadLocal对象-前台用户
    private static final ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>() ;

    // 保存-后台用户
    public static void set(SysUser sysUser) {
        threadLocal.set(sysUser);
    }

    // 获取-后台用户
    public static SysUser get() {
        return threadLocal.get() ;
    }

    // 删除-后台用户
    public static void remove() {
        threadLocal.remove();
    }

    // 保存-前台用户
    public static void setUserInfo(UserInfo userInfo) {
        userInfoThreadLocal.set(userInfo);
    }

    // 获取-前台用户
    public static UserInfo getUserInfo() {
        return userInfoThreadLocal.get();
    }

    // 删除-前台用户
    public static void removeUserInfo() {
        userInfoThreadLocal.remove();
    }
}