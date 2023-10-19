package com.atguigu.spzx.mapper;

import com.atguigu.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-18
 */
@Mapper
public interface OrderInfoMapper {

    OrderStatistics selectOrderTotalAmount(String data);
}
