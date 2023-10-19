package com.atguigu.spzx.service;


import com.atguigu.spzx.model.dto.order.OrderStatisticsDto;
import com.atguigu.spzx.model.entity.order.OrderStatistics;
import com.atguigu.spzx.model.vo.order.OrderStatisticsVo;

/**
 * <p>
 * 订单统计 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-18
 */
public interface OrderStatisticsService {

    void add(OrderStatistics orderStatistics);

    OrderStatisticsVo findOrderTotalAmount(OrderStatisticsDto orderStatisticsDto);
}
