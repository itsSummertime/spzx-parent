package com.atguigu.spzx.order.service.impl;

import com.atguigu.spzx.common.AuthContextUtil;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.feign.cart.CartFeignClient;
import com.atguigu.spzx.feign.product.ProductFeignClient;
import com.atguigu.spzx.feign.user.UserFeignClient;
import com.atguigu.spzx.model.dto.h5.OrderInfoDto;
import com.atguigu.spzx.model.entity.h5.CartInfo;
import com.atguigu.spzx.model.entity.order.OrderInfo;
import com.atguigu.spzx.model.entity.order.OrderItem;
import com.atguigu.spzx.model.entity.order.OrderLog;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.entity.user.UserAddress;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.h5.TradeVo;
import com.atguigu.spzx.order.mapper.OrderInfoMapper;
import com.atguigu.spzx.order.mapper.OrderItemMapper;
import com.atguigu.spzx.order.mapper.OrderLogMapper;
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
    OrderInfoMapper orderInfoMapper;
    @Autowired
    private ProductFeignClient productFeignClient;
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private CartFeignClient cartFeignClient;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderLogMapper orderLogMapper;
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
        return tradeVo;
    }

    @Override
    public long submitOrder(OrderInfoDto orderInfoDto) {
        //获取前端提交的参数
        Long userAddressId = orderInfoDto.getUserAddressId(); //送货地址id
        BigDecimal feightFee = orderInfoDto.getFeightFee(); //运费
        String remark = orderInfoDto.getRemark(); //备注
        List<OrderItem> orderItemList = orderInfoDto.getOrderItemList(); //订单明细(商品列表)

        //订单总额
        BigDecimal totalAmount = new BigDecimal(0);

        //遍历商品列表
       for (OrderItem orderItem : orderItemList){
           //OpenFeign远程调用商品服务，获取商品sku信息
           ProductSku productSku = productFeignClient.findById(orderItem.getSkuId());


           //判断库存不够的情况：要买的数量 > 库存数量
           if (orderItem.getSkuNum() > productSku.getStockNum()) {
               throw new GuiguException(ResultCodeEnum.STOCK_LESS);
           }

           //计算当前商品的价格： 单价*数量
           BigDecimal multiply = orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum()));
           //累加到订单总额
           totalAmount = totalAmount.add(multiply);
       }

        //OpenFeign远程调用用户服务，获取地址信息
        UserAddress userAddress = userFeignClient.findById(userAddressId);
        //从线程变量中获取当前用户信息
        UserInfo userInfo = AuthContextUtil.getUserInfo();

        //提取订单数据
        OrderInfo orderInfo = new OrderInfo();
        //当前用户信息>>>
        orderInfo.setUserId(userInfo.getId());
        orderInfo.setNickName(userInfo.getNickName());
        //收货信息>>>
        orderInfo.setReceiverName(userAddress.getName());
        orderInfo.setReceiverPhone(userAddress.getPhone());
        orderInfo.setReceiverTagName(userAddress.getTagName());
        orderInfo.setReceiverProvince(userAddress.getProvinceCode());
        orderInfo.setReceiverCity(userAddress.getCityCode());
        orderInfo.setReceiverDistrict(userAddress.getDistrictCode());
        orderInfo.setReceiverAddress(userAddress.getAddress());
        //订单信息>>>
        orderInfo.setOrderNo(String.valueOf(System.currentTimeMillis()));
        orderInfo.setTotalAmount(totalAmount);
        orderInfo.setOriginalTotalAmount(totalAmount);
        orderInfo.setFeightFee(feightFee);
        orderInfo.setPayType(2);
        orderInfo.setRemark(remark);

        //添加到数据库
        orderInfoMapper.insert(orderInfo);

        //获取数据库生产的订单id
        Long orderInfoId = orderInfo.getId();


        //2.保存订单项 > order_item表  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        //遍历商品列表，设置每个商品的订单id
        orderItemList.forEach(orderItem -> {
            orderItem.setOrderId(orderInfoId);
        });

        //批量添加到数据库
        orderItemMapper.insertBatch(orderItemList);
        //3.保存订单日志 > order_log表  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        //订单日志信息
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderId((orderInfoId));
        orderLog.setOperateUser("用户");
        orderLog.setProcessStatus(0); //未付款
        orderLog.setNote("提交订单");
        //添加到数据库
        orderLogMapper.insert(orderLog);

        return orderInfoId;
    }
}
