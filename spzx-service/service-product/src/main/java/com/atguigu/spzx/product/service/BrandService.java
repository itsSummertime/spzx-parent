package com.atguigu.spzx.product.service;

import com.atguigu.spzx.model.entity.product.Brand;

import java.util.List;

/**
 * <p>
 * 分类品牌 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-24
 */
public interface BrandService {

    List<Brand> findAll();
}
