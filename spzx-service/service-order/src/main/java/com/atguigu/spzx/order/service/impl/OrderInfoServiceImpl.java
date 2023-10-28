package com.atguigu.spzx.order.service.impl;

import com.atguigu.spzx.feign.cart.CartFeignClient;
import com.atguigu.spzx.model.entity.h5.CartInfo;
import com.atguigu.spzx.model.entity.order.OrderItem;
import com.atguigu.spzx.model.vo.h5.TradeVo;
import com.atguigu.spzx.order.service.OrderInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-27
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private CartFeignClient cartFeignClient;
    @Override
    public TradeVo trade() {
        //OpenFeign远程调用购物车服务，获取已选的商品列表
        List<CartInfo> cartInfoList = cartFeignClient.getChecked();

        //提取响应给前端的数据-结算商品列表、结算总金额  >>>>>>>>>>>>>>>>>>
        //需要返回的 - 结算商品列表
        List<OrderItem> orderItemList = new ArrayList<>();
        //需要返回的 - 结算总金额

        BigDecimal totalAmount = new BigDecimal(0);
        //需要返回的 - 结算总金额
        for (CartInfo cartInfo : cartInfoList) {
            OrderItem orderItem = new OrderItem();
            //复制属性值
            BeanUtils.copyProperties(cartInfo,orderItem);
            //不一样的属性名，单独设置值
            orderItem.setThumbImg(cartInfo.getImgUrl());
            orderItem.setSkuPrice(cartInfo.getCartPrice());

            //添加到结算商品列表中
            orderItemList.add(orderItem);

            //计算当前商品的金额 = 单价*数量
            BigDecimal multiply = orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum()));
            //累加到结算总金额
            totalAmount = totalAmount.add(multiply);

        }

        //封装数据
        TradeVo tradeVo = new TradeVo();
        tradeVo.setOrderItemList(orderItemList);
        tradeVo.setTotalAmount(totalAmount);
        return null;
    }
}
