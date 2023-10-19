package com.atguigu.spzx.mapper;
import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-17
 */
@Mapper
public interface ProductMapper {

    List<Product> selectByPage(ProductDto productDto);

    void insert(Product product);

    Product selectById(long id);

    void update(Product product);

    void delete(long id);

    void updateAuditStatus(long id, int auditStatus);

    void updateStatus(long id, int status);
}
