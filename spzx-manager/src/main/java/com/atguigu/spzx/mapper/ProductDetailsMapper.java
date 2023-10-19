package com.atguigu.spzx.mapper;

import com.atguigu.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品详情图片 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-14
 */
@Mapper
public interface ProductDetailsMapper {
    void insert(ProductDetails productDetails);

    ProductDetails selectByProductId(long productId);

    void update(ProductDetails productDetails);

    void deleteByProductId(long productId);
}

