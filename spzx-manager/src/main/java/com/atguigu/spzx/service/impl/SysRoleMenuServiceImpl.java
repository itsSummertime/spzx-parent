package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.common.annotation.Log;
import com.atguigu.spzx.mapper.SysRoleMenuMapper;
import com.atguigu.spzx.model.dto.system.AssignMenuDto;
import com.atguigu.spzx.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色菜单 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-13
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Log(title = "角色菜单管理:分配菜单")
    @Transactional //开启事务
    @Override
    public void assignMenu(AssignMenuDto assignMenuDto) {
        //1.删除原来分配的菜单
        sysRoleMenuMapper.deleteByRoleId(assignMenuDto.getRoleId());

        //2.添加新分配的菜单
        sysRoleMenuMapper.insertBatch(assignMenuDto);
    }
}
