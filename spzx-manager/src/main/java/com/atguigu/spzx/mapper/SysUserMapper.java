package com.atguigu.spzx.mapper;

import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {
    static void insert(SysUser sysUser) {
    }

    SysUser selectByUserName(String userName);

    List<SysUser> selectByPage(SysUserDto sysUserDto);

    SysUser findByUsername(String userName);
}
