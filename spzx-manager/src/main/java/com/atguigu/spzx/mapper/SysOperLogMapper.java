package com.atguigu.spzx.mapper;


import com.atguigu.spzx.model.entity.system.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 操作日志记录 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-19
 */
@Mapper
public interface SysOperLogMapper  {

    void insert(SysOperLog sysOperLog);
}
