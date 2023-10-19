package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.mapper.BrandMapper;
import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分类品牌 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-14
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageInfo<Brand> findByPage(int pageNum, int pageSize) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        //执行查询
        List<Brand> list = brandMapper.selectAll();
        //封装查询结果到分页对象PageInfo中
        return new PageInfo<>(list);
    }

    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public List<Brand> findByCategoryId(long categoryId) {
        return brandMapper.selectByCategoryId(categoryId);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.update(brand);
    }

    @Override
    public void delete(long id) {
        brandMapper.delete(id);
    }

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }
}
