package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.mapper.ProductSpecMapper;
import com.atguigu.spzx.model.entity.product.ProductSpec;
import com.atguigu.spzx.service.ProductSpecService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品规格 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
@Service
public class ProductSpecServiceImpl implements ProductSpecService {

    @Autowired
    private ProductSpecMapper productSpecMapper;

    @Override
    public PageInfo<ProductSpec> findByPage(int pageNum, int pageSize) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        //执行查询
        List<ProductSpec> list = productSpecMapper.selectAll();
        //将查询结果封装到分页对象PageInfo
        return new PageInfo<>(list);
    }

    @Override
    public void add(ProductSpec productSpec) {
        productSpecMapper.insert(productSpec);
    }

    @Override
    public void update(ProductSpec productSpec) {
        productSpecMapper.update(productSpec);
    }

    @Override
    public void delete(long id) {
        productSpecMapper.delete(id);
    }

    @Override
    public List<ProductSpec> findAll() {
        return productSpecMapper.selectAll();
    }
}
