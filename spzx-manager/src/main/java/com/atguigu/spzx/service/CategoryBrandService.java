package com.atguigu.spzx.service;


import com.atguigu.spzx.model.dto.product.CategoryBrandDto;
import com.atguigu.spzx.model.entity.product.CategoryBrand;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 分类品牌 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-17
 */
public interface CategoryBrandService {

    PageInfo<CategoryBrand> findByPage(int pageNum, int pageSize, CategoryBrandDto categoryBrandDto);

    void add(CategoryBrand categoryBrand);

    void update(CategoryBrand categoryBrand);

    void delete(long id);
}
