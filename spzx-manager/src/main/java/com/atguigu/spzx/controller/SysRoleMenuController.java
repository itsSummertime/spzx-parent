package com.atguigu.spzx.controller;


import com.atguigu.spzx.model.dto.system.AssignMenuDto;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.service.SysRoleMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色菜单 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
@Tag(name="角色菜单接口")
@RestController
@RequestMapping("/admin/system/sysRoleMenu")
public class SysRoleMenuController {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Operation(summary = "分配菜单")
    @PostMapping("/assignMenu")
    public Result<T> assignMenu(@RequestBody AssignMenuDto assignMenuDto){
        sysRoleMenuService.assignMenu(assignMenuDto);
        return Result.ok();
    }
}

