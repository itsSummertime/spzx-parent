package com.atguigu.spzx.controller;

import com.atguigu.spzx.service.SysUserService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//后台登录接口
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

   @Autowired
   private SysUserService sysUserService;
    @PostMapping ("/login")
    public Result<LoginDto> login(@RequestBody LoginDto loginDto){
        LoginVo loginVo = sysUserService.login(loginDto) ;
        return Result.ok(loginDto);
    }


}
