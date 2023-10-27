package com.atguigu.spzx.user.mapper;

import com.atguigu.spzx.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-26
 */
@Mapper
public interface UserInfoMapper{

    UserInfo selectByUsername(String username);

    void insert(UserInfo userInfo);
}
