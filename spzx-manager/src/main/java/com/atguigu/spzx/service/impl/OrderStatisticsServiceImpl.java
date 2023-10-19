package com.atguigu.spzx.service.impl;


import com.atguigu.spzx.mapper.OrderStatisticsMapper;
import com.atguigu.spzx.model.dto.order.OrderStatisticsDto;
import com.atguigu.spzx.model.entity.order.OrderStatistics;
import com.atguigu.spzx.model.vo.order.OrderStatisticsVo;
import com.atguigu.spzx.service.OrderStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单统计 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-18
 */
@Service
public class OrderStatisticsServiceImpl  implements OrderStatisticsService {

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;
    @Override
    public void add(OrderStatistics orderStatistics) {
        orderStatisticsMapper.insert(orderStatistics);
    }

    @Override
    public OrderStatisticsVo findOrderTotalAmount(OrderStatisticsDto orderStatisticsDto) {
        //查询订单统计数据
        List<OrderStatistics> list = orderStatisticsMapper.selectOrderTotalAmount(orderStatisticsDto);
        //提取集合中的orderDate字段,添加到一个新的List集合中
        List<Date> dateList = list.stream().map(OrderStatistics::getOrderDate).toList();
        //提取List集合中的totalAmount字段，添加到一个新的List集合中
        List<BigDecimal> totalAmountList = list.stream().map(OrderStatistics::getTotalAmount).toList();

        //封装到返回给前端的vo对象中
        OrderStatisticsVo vo = new OrderStatisticsVo();
        vo.setDateList(dateList);
        vo.setAmountList(totalAmountList);
        return vo;
    }
}
