package com.atguigu.spzx.mapper;

import com.atguigu.spzx.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 商品规格 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
@Mapper
public interface ProductSpecMapper {

    List<ProductSpec> selectAll();

    void insert(ProductSpec productSpec);

    void update(ProductSpec productSpec);

    void delete(long id);
}