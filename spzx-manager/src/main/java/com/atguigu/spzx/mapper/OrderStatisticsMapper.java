package com.atguigu.spzx.mapper;

import com.atguigu.spzx.model.dto.order.OrderStatisticsDto;
import com.atguigu.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 订单统计 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-18
 */
@Mapper
public interface OrderStatisticsMapper {

    void insert(OrderStatistics orderStatistics);

    List<OrderStatistics> selectOrderTotalAmount(OrderStatisticsDto orderStatisticsDto);
}
