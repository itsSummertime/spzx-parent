package com.atguigu.spzx.service.impl;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.mapper.SysUserMapper;
import com.atguigu.spzx.service.SysUserService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.concurrent.TimeUnit;


@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public LoginVo login(LoginDto loginDto) {
        //获取参数
        String userName = loginDto.getUserName();
        String password = loginDto.getPassword();

        SysUser sysUser = sysUserMapper.selectByUserName(userName);
        if (sysUser == null) {
            throw new RuntimeException("用户名不存在");
        }
        String md5password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!sysUser.getPassword().equals(md5password)) {
            throw new RuntimeException("密码输入错误");
        }
        //使用UUID生成全局唯一的token令牌
        String token = IdUtil.simpleUUID();
        //将token令牌保存在redis
        String key = "user:login"+token;
        String value =JSON.toJSONString(sysUser);
        redisTemplate.opsForValue().set(key, value,30, TimeUnit.MINUTES);
        //响应给前端参数
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token("");
        return loginVo;
    }
}
