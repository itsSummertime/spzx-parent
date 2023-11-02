package com.atguigu.spzx.order.mapper;

import com.atguigu.spzx.model.entity.order.OrderLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单日志 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-27
 */
@Mapper
public interface OrderLogMapper {
    void insert(OrderLog orderLog);
}
