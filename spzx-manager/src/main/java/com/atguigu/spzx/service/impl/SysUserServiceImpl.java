package com.atguigu.spzx.service.impl;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.mapper.SysUserMapper;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import com.atguigu.spzx.service.SysUserService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public LoginVo login(LoginDto loginDto) {
        //获取前端页面填写的验证码 和 验证码对应的key
        String captcha = loginDto.getCaptcha();
        String codeKey = loginDto.getCodeKey();
        //使用codeKey到Redis中获取对应的验证码
        String captchaRedis = redisTemplate.opsForValue().get("user:login:captcha:" + codeKey);
        //比较填写的验证码 和 Redis中的验证码
        if(StrUtil.isEmpty(captcha) || !captcha.equalsIgnoreCase(captchaRedis)){
            throw new GuiguException(ResultCodeEnum.CAPTCHA_ERROR);
        }
        //验证码输入正确后删除
        redisTemplate.delete("user:login:captcha:" + codeKey);
        //获取参数
        String userName = loginDto.getUserName();
        String password = loginDto.getPassword();

        SysUser sysUser = sysUserMapper.selectByUserName(userName);
        if (sysUser == null) {
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
            //throw new RuntimeException("用户名或者密码错误") ;
        }
        String md5password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!sysUser.getPassword().equals(md5password)) {
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
            //throw new RuntimeException("用户名或者密码错误") ;
        }
        //使用UUID生成全局唯一的token令牌
        String token = IdUtil.simpleUUID();
        //将token令牌保存在redis
        String key = "user:login:"+token;
        String value =JSON.toJSONString(sysUser);
        redisTemplate.opsForValue().set(key, value,30, TimeUnit.MINUTES);
        //响应给前端参数
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token("");
        return loginVo;
    }

    @Override
    public SysUser getUserInfo(String token) {
        //根据token令牌获取对应用户信息
        String string = redisTemplate.opsForValue().get("user:login:"+token);
        //将JSON字符串转换为SysUser对象
        return JSON.parseObject(string,SysUser.class);
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login:"+token);
    }

    @Override
    public ValidateCodeVo getCaptcha() {
        //生成图片验证码  参数：宽、高、验证码位数、干扰线数量
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 50, 4, 20);
        //获取验证码内容 1cq2
        String code = circleCaptcha.getCode();
        //获取图片base64
        String imageBase64 = circleCaptcha.getImageBase64();

        //验证码对应的key
        String codeKey = IdUtil.simpleUUID();
        //保存到Redis中，有效期2分钟
        redisTemplate.opsForValue().set("user:login:captcha:" + codeKey, code, 2, TimeUnit.MINUTES);

        //响应给前端的数据
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(codeKey); //验证码对应的key
        validateCodeVo.setCodeValue(imageBase64); //验证码的图片
        return validateCodeVo;
    }

    @Override
    public PageInfo<SysUser> findByPage(int pageNum, int pageSize, SysUserDto sysUserDto) {
        //执行查询前，开启分页
        PageHelper.startPage(pageNum, pageSize);
        //执行查询
        List<SysUser> list = sysUserMapper.selectByPage(sysUserDto);
        //将查询结果封装到PageInfo分页对象中
        return new PageInfo<>(list);
    }

    @Override
    public void add(SysUser sysUser) {
        //根据输入的用户查询用户名
        SysUser dbSysUser = sysUserMapper.findByUsername(sysUser.getUserName());
        if (dbSysUser != null) {
            throw new GuiguException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        //对密码进行加密
        String password = sysUser.getPassword();
        String passwordDigest = DigestUtils.md5DigestAsHex(password.getBytes());
        SysUserMapper.insert(sysUser);

    }

}
