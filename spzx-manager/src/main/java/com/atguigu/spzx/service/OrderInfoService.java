package com.atguigu.spzx.service;


import com.atguigu.spzx.mapper.OrderInfoMapper;
import com.atguigu.spzx.model.entity.order.OrderStatistics;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-18
 */
public interface OrderInfoService {
    OrderStatistics getOrderTotalAmount(String data);
}
