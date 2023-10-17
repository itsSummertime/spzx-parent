package com.atguigu.spzx.service;


import com.atguigu.spzx.model.entity.product.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 分类品牌 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
public interface BrandService  {

    PageInfo<Brand> findByPage(int pageNum, int pageSize);

    void update(Brand brand);

    void delete(long id);

    List<Brand> findAll();

    void add(Brand brand);
}
