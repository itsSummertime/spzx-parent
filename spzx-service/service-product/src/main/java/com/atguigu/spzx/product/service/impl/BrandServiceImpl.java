package com.atguigu.spzx.product.service.impl;

import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.product.mapper.BrandMapper;
import com.atguigu.spzx.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分类品牌 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-24
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    //unless的值如果为true，则不会添加缓存
    @Cacheable(value = "brand", key = "'findAll'", unless = "#result.size()==0")
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }
}
