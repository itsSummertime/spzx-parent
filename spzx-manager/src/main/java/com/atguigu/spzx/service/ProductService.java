package com.atguigu.spzx.service;

import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
public interface ProductService {

    PageInfo<Product> findByPage(int pageNum, int pageSize, ProductDto productDto);

    void add(Product product);

    Product findById(long id);

    void update(Product product);

    void delete(long id);

    void updateAuditStatus(long id, int auditStatus);

    void updateStatus(long id, int status);
}
