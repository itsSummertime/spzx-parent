package com.atguigu.spzx.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 角色菜单 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
@Mapper
public interface SysRoleMenuMapper {

    List<Long> selectAssignMenuList(long roleId);
}
