package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.common.AuthContextUtil;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.mapper.SysMenuMapper;
import com.atguigu.spzx.mapper.SysRoleMenuMapper;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.service.SysMenuService;
import com.atguigu.spzx.utils.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-13
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> findTreeList() {
        //查询所有菜单
        List<SysMenu> allList  = sysMenuMapper.selectAll();
        //转为树节点菜单

          return MenuUtil.toTreeList(allList);
    }

    @Override
    public void add(SysMenu sysMenu) {
        sysMenuMapper.insert(sysMenu);
    }

    @Override
    public void update(SysMenu sysMenu) {
        sysMenuMapper.update(sysMenu);
    }

    @Override
    public void delete(long id) {
        //判断是否有子菜单, 查询parent_id = id的数量
        int count = sysMenuMapper.countByParentId(id);
        if(count == 0){
            //没有子菜单才能删除
            sysMenuMapper.deleteById(id);
        }else{
            //抛出异常
            throw new GuiguException(ResultCodeEnum.NODE_ERROR);
        }
    }

    @Override
    public Map<String, Object> findAssignMenuList(long roleId) {
        //1.菜单树节点列表  sys_menu表
        List<SysMenu> treeList = this.findTreeList();
        //2.指定角色已分配且已选中的菜单id列表 sys_role_menu表
        List<Long> menuIdList = sysRoleMenuMapper.selectAssignMenuList(roleId);

        //封装响应给前端的Map
        Map<String, Object> map = new HashMap<>();
        map.put("treeList", treeList);
        map.put("menuIdList", menuIdList);
        return map;
    }

    @Override
    public List<SysMenu> findAccessMenuList() {
        //从线程变量中获取用户id
        Long userId = AuthContextUtil.get().getId();

        //根据用户id查询可访问的菜单列表
        List<SysMenu> menuList = sysMenuMapper.selectByUserId(userId);
        //转为树节点的结构
        return MenuUtil.toTreeList(menuList);
    }
}
