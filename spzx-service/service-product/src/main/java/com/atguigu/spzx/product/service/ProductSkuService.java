package com.atguigu.spzx.product.service;

import com.atguigu.spzx.model.entity.product.ProductSku;

import java.util.List;

/**
 * <p>
 * 商品sku 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-24
 */
public interface ProductSkuService{

    List<ProductSku> findHotProduct();
}
