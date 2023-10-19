package com.atguigu.spzx.service;

import com.atguigu.spzx.model.entity.product.ProductSpec;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 商品规格 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
public interface ProductSpecService {

    PageInfo<ProductSpec> findByPage(int pageNum, int pageSize);

    void add(ProductSpec productSpec);

    void update(ProductSpec productSpec);

    void delete(long id);

    List<ProductSpec> findAll();
}