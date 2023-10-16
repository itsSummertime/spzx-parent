package com.atguigu.spzx.service.impl;


import com.atguigu.spzx.mapper.CategoryMapper;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品分类 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
@Service
public class CategoryServiceImpl  implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findNextList(long parentId) {
        //1.根据parent_id查询分类列表
        List<Category> list = categoryMapper.selectByParentId(parentId);

        //2.判断查到的每个分类是否还有下一级
        list.forEach(category -> {
            //根据parent_id查询分类的数量
            int count = categoryMapper.countByParentId(category.getId());
            if(count > 0){
                //有下一级分类
                category.setHasChildren(true);
            }
        });

        return list;
    }
}
