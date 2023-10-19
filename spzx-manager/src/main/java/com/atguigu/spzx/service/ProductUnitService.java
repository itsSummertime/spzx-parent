package com.atguigu.spzx.service;

import com.atguigu.spzx.model.entity.base.ProductUnit;

import java.util.List;

/**
 * <p>
 * 商品单位 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-17
 */
public interface ProductUnitService {

    List<ProductUnit> findAll();
}