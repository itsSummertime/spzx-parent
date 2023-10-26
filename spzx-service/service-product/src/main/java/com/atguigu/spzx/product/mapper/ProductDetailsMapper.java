package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品详情图 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-24
 */
@Mapper
public interface ProductDetailsMapper {

    ProductDetails selectByProductId(Long productId);
}
