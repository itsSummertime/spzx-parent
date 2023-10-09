package com.atguigu.spzx.controller;

import com.atguigu.spzx.common.AuthContextUtil;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import com.atguigu.spzx.service.SysUserService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.system.LoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.formula.functions.T;
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
        //从线程变量中获取SysUser对象
        SysUser sysUser = AuthContextUtil.get();
        return Result.ok(sysUser);
    }
    @Operation(summary = "退出")
    @GetMapping("/logout")
    public Result<T> logout(@RequestHeader String token) {
        sysUserService.logout(token);
        return Result.ok();
    }
    @Operation(summary = "生成图片验证码")
    @GetMapping("/getCaptcha")
    public Result<ValidateCodeVo> getCaptcha() {
        ValidateCodeVo validateCodeVo = sysUserService.getCaptcha();
        return Result.ok(validateCodeVo);
    }

}
