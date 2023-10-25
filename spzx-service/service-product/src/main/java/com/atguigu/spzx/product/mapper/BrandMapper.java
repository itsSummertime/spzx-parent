package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 分类品牌 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-24
 */
@Mapper
public interface BrandMapper {

    List<Brand> selectAll();
}
