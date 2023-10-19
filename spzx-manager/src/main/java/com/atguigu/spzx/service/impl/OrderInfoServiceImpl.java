package com.atguigu.spzx.service.impl;


import com.atguigu.spzx.mapper.OrderInfoMapper;
import com.atguigu.spzx.model.entity.order.OrderStatistics;
import com.atguigu.spzx.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-18
 */
@Service
public class OrderInfoServiceImpl  implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public OrderStatistics getOrderTotalAmount(String data) {
        return orderInfoMapper.selectOrderTotalAmount(data);
    }
}
