package com.atguigu.spzx.controller;

import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.service.SysUserService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.system.LoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//后台登录接口
@Tag(name="后台登录接口")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

   @Autowired
   private SysUserService sysUserService;
    @PostMapping ("/login")
    @Operation(summary = "登录")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto){
        LoginVo loginVo = sysUserService.login(loginDto) ;
        return Result.ok(loginVo);
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/getUserInfo")
    public Result<SysUser> getUserInfo(@RequestHeader String token){
        SysUser sysUser = sysUserService.getUserInfo(token);
        return Result.ok(sysUser);
    }


}
