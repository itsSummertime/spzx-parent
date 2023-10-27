package com.atguigu.spzx.product.service;

import com.atguigu.spzx.model.dto.h5.ProductSkuDto;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.h5.ProductItemVo;
import com.github.pagehelper.PageInfo;

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

    PageInfo<ProductSku> findByPage(int page, int limit, ProductSkuDto productSkuDto);

    ProductItemVo item(long skuId);

    ProductSku findById(long skuId);
}
