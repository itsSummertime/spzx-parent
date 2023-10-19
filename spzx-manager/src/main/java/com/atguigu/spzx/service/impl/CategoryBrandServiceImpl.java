package com.atguigu.spzx.service.impl;


import com.atguigu.spzx.mapper.CategoryBrandMapper;
import com.atguigu.spzx.model.dto.product.CategoryBrandDto;
import com.atguigu.spzx.model.entity.product.CategoryBrand;
import com.atguigu.spzx.service.CategoryBrandService;

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
 * @since 2023-10-17
 */
@Service
public class CategoryBrandServiceImpl implements CategoryBrandService {

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Override
    public PageInfo<CategoryBrand> findByPage(int pageNum, int pageSize, CategoryBrandDto categoryBrandDto) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        //执行查询
        List<CategoryBrand> list = categoryBrandMapper.selectByPage(categoryBrandDto);
        //将查询结果封装到分页对象PageInfo
        return new PageInfo<>(list);
    }

    @Override
    public void add(CategoryBrand categoryBrand) {
        categoryBrandMapper.insert(categoryBrand);
    }

    @Override
    public void update(CategoryBrand categoryBrand) {
        categoryBrandMapper.update(categoryBrand);
    }

    @Override
    public void delete(long id) {
        categoryBrandMapper.delete(id);
    }
}
