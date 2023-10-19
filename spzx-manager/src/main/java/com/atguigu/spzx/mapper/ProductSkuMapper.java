package com.atguigu.spzx.mapper;

import com.atguigu.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 商品sku Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-14
 */
@Mapper
public interface ProductSkuMapper {
    void insertBatch(List<ProductSku> skuList);

    List<ProductSku> selectByProductId(long id);

    void update(ProductSku productSku);

    void deleteByProductId(long id);
}