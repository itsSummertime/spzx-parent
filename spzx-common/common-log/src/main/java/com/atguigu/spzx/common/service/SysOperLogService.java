package com.atguigu.spzx.common.service;

import com.atguigu.spzx.model.entity.system.SysOperLog;

/**
 * <p>
 * 操作日志记录 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-18
 */
public interface SysOperLogService {

    void add(SysOperLog sysOperLog);
}