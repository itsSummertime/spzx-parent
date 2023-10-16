package com.atguigu.spzx.service;


import com.atguigu.spzx.model.entity.product.Category;

import java.util.List;

/**
 * <p>
 * 商品分类 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
public interface CategoryService {

    List<Category> findNextList(long parentId);
}
