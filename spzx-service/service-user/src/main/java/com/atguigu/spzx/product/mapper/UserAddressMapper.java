package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户地址表 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-27
 */
@Mapper
public interface UserAddressMapper{

    List<UserAddress> selectByUserId(Long userId);
}
