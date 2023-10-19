package com.atguigu.spzx.controller;


import com.atguigu.spzx.model.dto.order.OrderStatisticsDto;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.order.OrderStatisticsVo;
import com.atguigu.spzx.service.OrderStatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 订单统计 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-18
 */

@Tag(name = "订单统计接口")
@RestController
@RequestMapping("admin/order/orderStatistics")
public class OrderStatisticsController {

    @Autowired
    private OrderStatisticsService orderStatisticsService;

    @Operation(summary = "统计订单总金额")
    @PostMapping("/findOrderTotalAmount")
    public Result<OrderStatisticsVo> findOrderTotalAmount(@RequestBody OrderStatisticsDto orderStatisticsDto){
        //响应的是vo,包含日期列表dateList,总金额列表amountList
        OrderStatisticsVo orderStatisticsVo = orderStatisticsService.findOrderTotalAmount(orderStatisticsDto);
        return Result.ok(orderStatisticsVo);
    }

}

