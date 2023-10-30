package com.atguigu.spzx.order.controller;


import com.atguigu.spzx.model.dto.h5.OrderInfoDto;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.h5.TradeVo;
import com.atguigu.spzx.order.service.OrderInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-27
 */
@Tag(name = "订单接口")
@RestController
@RequestMapping("/orderInfo")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    @Operation(summary = "确认下单")
    @GetMapping("/auth/trade")
    public Result<TradeVo> trade() {
        TradeVo tradeVo = orderInfoService.trade();
        return Result.ok(tradeVo);
    }

    @Operation(summary = "提交订单")
    @GetMapping("/auth/submitOrder")
    public Result<Long> submitOrder(@RequestBody OrderInfoDto orderInfoDto) {
        //订单创建之后，返回订单id给前端
        long orderId = orderInfoService.submitOrder(orderInfoDto);
        return Result.ok(orderId);
    }
}

