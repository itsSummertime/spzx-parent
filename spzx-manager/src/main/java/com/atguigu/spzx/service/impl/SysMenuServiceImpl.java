package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.mapper.SysMenuMapper;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.service.SysMenuService;
import com.atguigu.spzx.utils.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
