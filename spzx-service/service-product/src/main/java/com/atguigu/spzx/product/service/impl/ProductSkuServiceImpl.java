package com.atguigu.spzx.product.service.impl;

import com.atguigu.spzx.model.dto.h5.ProductSkuDto;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.product.mapper.ProductSkuMapper;
import com.atguigu.spzx.product.service.ProductSkuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public PageInfo<ProductSku> findByPage(int page, int limit, ProductSkuDto productSkuDto) {
        //开启分页
        PageHelper.startPage(page, limit);
        //执行查询
        List<ProductSku> list = productSkuMapper.selectByPage(productSkuDto);
        //封装查询的结果到分页对象PageInfo
        return new PageInfo<>(list);
    }
}
