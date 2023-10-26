package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-24
 */
@Mapper
public interface ProductMapper {

    Product selectById(Long productId);
}
