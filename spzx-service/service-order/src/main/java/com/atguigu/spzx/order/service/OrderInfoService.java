package com.atguigu.spzx.order.service;

import com.atguigu.spzx.model.dto.h5.OrderInfoDto;
import com.atguigu.spzx.model.vo.h5.TradeVo;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-27
 */
public interface OrderInfoService {

    TradeVo trade();

    long submitOrder(OrderInfoDto orderInfoDto);
}
