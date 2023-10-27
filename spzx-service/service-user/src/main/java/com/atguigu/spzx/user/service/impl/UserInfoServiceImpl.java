package com.atguigu.spzx.user.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.AuthContextUtil;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.model.dto.h5.UserLoginDto;
import com.atguigu.spzx.model.dto.h5.UserRegisterDto;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.h5.UserInfoVo;
import com.atguigu.spzx.user.controller.mapper.UserInfoMapper;
import com.atguigu.spzx.user.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-26
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public void register(UserRegisterDto userRegisterDto) {
        //获取前端页面填写的参数
        String username = userRegisterDto.getUsername(); //用户名(手机号)
        String password = userRegisterDto.getPassword(); //密码
        String nickName = userRegisterDto.getNickName(); //昵称
        String code = userRegisterDto.getCode(); //验证码

        //1.验证码的校验>>>>>>>>>>>>>
        //获取Redis中的验证码
        String codeDB = redisTemplate.opsForValue().get("user:code:" + username);
        //对比前端填写的验证码  和  Redis中的验证码
        if (!code.equals(codeDB)) {
            throw new GuiguException(ResultCodeEnum.CAPTCHA_ERROR);
        }

        //2.用户名的校验>>>>>>>>>>>>>>>
        //根据用户名查询用户信息
        UserInfo userInfo = userInfoMapper.selectByUsername(username);
        if (userInfo != null) {
            //用户名已存在
            throw new GuiguException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        //3.添加用户信息
        userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPhone(username);
        userInfo.setNickName(nickName);
        //对密码进行MD5加密
        String passwordMD5 = DigestUtils.md5DigestAsHex(password.getBytes());
        userInfo.setPassword(passwordMD5);
        //添加到数据库
        userInfoMapper.insert(userInfo);

    }

    @Override
    public String login(UserLoginDto userLoginDto) {
        //获取前端提交的参数
        String username = userLoginDto.getUsername(); //用户名
        String password = userLoginDto.getPassword(); //密码(明文)

        //1.用户名的校验>>>>>>>>>>
        UserInfo userInfo = userInfoMapper.selectByUsername(username);
        if (userInfo == null) {
            //用户名或密码错误
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }

        //2.密码的校验>>>>>>>>>>
        //对前端提交的密码进行MD5加密
        String passwordMD5 = DigestUtils.md5DigestAsHex(password.getBytes());
        //比较数据库中的密码  和   前端填写的密码
        if (!userInfo.getPassword().equals(passwordMD5)) {
            //用户名或密码错误
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }

        //3.登录成功，生成token令牌
        String token = IdUtil.simpleUUID();
        //用户对象转为json字符串
        String jsonString = JSON.toJSONString(userInfo);
        //保存到Redis中，有效期7天
        redisTemplate.opsForValue().set("user:login:" + token, jsonString, 7, TimeUnit.DAYS);
        //返回token令牌
        return token;
    }

    @Override
    public UserInfoVo getCurrentUserInfo() {
        //从线程变量中获取UserInfo
        UserInfo userInfo = AuthContextUtil.getUserInfo();

        //提取数据返回
        UserInfoVo vo = new UserInfoVo();
        //复制属性, 注意：两个对象的属性名需要一致
        BeanUtils.copyProperties(userInfo, vo);
        return vo;
    }
}
