package com.atguigu.spzx.controller;


import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-13
 */
@Tag(name = "菜单接口")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;
    @Operation(summary = "查询树节点菜单")
    @GetMapping("/findTreeList")
    public Result<List<SysMenu>> findTreeList(){
        List<SysMenu> list = sysMenuService.findTreeList();
        return Result.ok(list);
    }

    @Operation(summary = "添加")
    @PostMapping("/add")
    public Result<T> add(@RequestBody SysMenu sysMenu){
        sysMenuService.add(sysMenu);
        return Result.ok();
    }

    @Operation(summary = "修改")
    @PutMapping("/update")
    public Result<T> update(@RequestBody SysMenu sysMenu){
        sysMenuService.update(sysMenu);
        return Result.ok();
    }

    @Operation(summary = "逻辑删除")
    @DeleteMapping("/delete/{id}")
    public Result<T> delete(@PathVariable long id) {
        sysMenuService.delete(id);
        return Result.ok();
    }
    @Operation(summary = "查询分配菜单列表")
    @GetMapping("/findAssignMenuList/{roleId}")
    public Result<Map<String,Object>> findAssignMenuList(@PathVariable long roleId){
        //包含菜单树节点列表、指定角色已分配且已选中的菜单id列表
        Map<String,Object> map = sysMenuService.findAssignMenuList(roleId);
        return Result.ok(map);
    }

    @Operation(summary = "查询可访问的菜单")
    @GetMapping("/findAccessMenuList")
    public Result<List<SysMenu>> findAccessMenuList() {
        List<SysMenu> list = sysMenuService.findAccessMenuList();
        return Result.ok(list);
    }
}

