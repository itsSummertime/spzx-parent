package com.atguigu.spzx.mapper;


import com.atguigu.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 分类品牌 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
@Mapper
public interface BrandMapper{

    List<Brand> selectAll();

    void delete(long id);

    void update(Brand brand);

    void insert(Brand brand);
}
