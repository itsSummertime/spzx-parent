package com.atguigu.spzx.service;


import com.atguigu.spzx.model.dto.system.AssignMenuDto;

/**
 * <p>
 * 角色菜单 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
public interface SysRoleMenuService {

    void assignMenu(AssignMenuDto assignMenuDto);
}
