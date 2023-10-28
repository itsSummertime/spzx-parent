package com.atguigu.spzx.product.service;

import com.atguigu.spzx.model.entity.user.UserAddress;

import java.util.List;

/**
 * <p>
 * 用户地址表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-27
 */
public interface UserAddressService {

    List<UserAddress> findUserAddressList();
}
