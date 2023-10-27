package com.atguigu.spzx.feign.product;

import com.atguigu.spzx.model.entity.product.ProductSku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-product", path = "api/product")
public interface ProductFeignClient {

    @GetMapping("/findById/{skuId}")
    public ProductSku findById(@PathVariable long skuId);
}
