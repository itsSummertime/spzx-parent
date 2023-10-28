package com.atguigu.spzx.feign.cart;

import com.atguigu.spzx.model.entity.h5.CartInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "service-cart", path = "/api/order/cart")
public interface CartFeignClient {
    @GetMapping("auth/getChecked")
    public List<CartInfo> getChecked();
}
