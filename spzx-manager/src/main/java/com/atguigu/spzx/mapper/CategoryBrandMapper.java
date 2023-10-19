package com.atguigu.spzx.mapper;


import com.atguigu.spzx.model.dto.product.CategoryBrandDto;
import com.atguigu.spzx.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 分类品牌 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-17
 */
@Mapper
public interface CategoryBrandMapper{

    List<CategoryBrand> selectByPage(CategoryBrandDto categoryBrandDto);

    void insert(CategoryBrand categoryBrand);

    void update(CategoryBrand categoryBrand);

    void delete(long id);
}
