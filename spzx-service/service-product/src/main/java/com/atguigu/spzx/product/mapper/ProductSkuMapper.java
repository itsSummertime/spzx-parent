package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.dto.h5.ProductSkuDto;
import com.atguigu.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 商品sku Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-24
 */
@Mapper
public interface ProductSkuMapper {

    List<ProductSku> findHotProduct();

    List<ProductSku> selectByPage(ProductSkuDto productSkuDto);

    ProductSku selectByID(long skuId);

    List<ProductSku> selectByProductId(Long productId);
}
