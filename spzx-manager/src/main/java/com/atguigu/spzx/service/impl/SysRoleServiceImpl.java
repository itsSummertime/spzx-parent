package com.atguigu.spzx.service.impl;


import com.atguigu.spzx.mapper.SysRoleMapper;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-10
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    private final SysRoleMapper sysRoleMapper;

    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    public PageInfo<SysRole> findByPage(int pageNum, int pageSize, SysRoleDto sysRoleDto) {
        //在查询之前,开启分页
        PageHelper.startPage(pageNum,pageSize);
        //执行查询
        List<SysRole> list = sysRoleMapper.selectByPage(sysRoleDto);
        return new PageInfo<>(list);
    }

    @Override
    public void add(SysRole sysRole) {
        sysRoleMapper.insert(sysRole);
    }

    @Override
    public void update(SysRole sysRole) {
        sysRoleMapper.update(sysRole);
    }

    @Override
    public void delete(long id) {
        sysRoleMapper.deleteById(id);
    }
}
