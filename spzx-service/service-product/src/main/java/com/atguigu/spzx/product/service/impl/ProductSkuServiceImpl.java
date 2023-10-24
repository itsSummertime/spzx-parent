package com.atguigu.spzx.product.service.impl;

import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.product.mapper.ProductSkuMapper;
import com.atguigu.spzx.product.service.ProductSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品sku 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-24
 */
@Service
public class ProductSkuServiceImpl implements ProductSkuService {
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Override
    public List<ProductSku> findHotProduct() {
        return productSkuMapper.findHotProduct();
    }
}
