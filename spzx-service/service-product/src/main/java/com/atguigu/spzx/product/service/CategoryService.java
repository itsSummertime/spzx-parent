package com.atguigu.spzx.product.service;

import com.atguigu.spzx.model.entity.product.Category;

import java.util.List;

/**
 * <p>
 * 商品分类 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-24
 */
public interface CategoryService{

    List<Category> findFistCategory();
}
