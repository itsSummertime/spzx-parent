package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.common.service.SysOperLogService;
import com.atguigu.spzx.mapper.SysOperLogMapper;
import com.atguigu.spzx.model.entity.system.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-18
 */
@Service
public class SysOperLogServiceImpl implements SysOperLogService {

    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    @Async //开启子线程运行该方法
    @Override
    public void add(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }
}
