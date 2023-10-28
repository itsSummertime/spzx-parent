package com.atguigu.spzx.product.service.impl;


import com.atguigu.spzx.common.AuthContextUtil;
import com.atguigu.spzx.model.entity.user.UserAddress;
import com.atguigu.spzx.product.mapper.UserAddressMapper;
import com.atguigu.spzx.product.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户地址表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-27
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Override
    public List<UserAddress> findUserAddressList() {
        //从线程变量中获取当前用用户id
        Long userId = AuthContextUtil.getUserInfo().getId();

        return  userAddressMapper.selectByUserId(userId);
    }
}
