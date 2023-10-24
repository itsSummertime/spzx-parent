package com.atguigu.spzx.product.service.impl;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.product.mapper.CategoryMapper;
import com.atguigu.spzx.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品分类 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-24
 */
@Service
public class CategoryServiceImpl  implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> findFistCategory() {
        return categoryMapper.findFistCategory();

    }

    @Override
    public List<Category> findCategoryTree() {
        //1.查询所有分分类
        List<Category> allList = categoryMapper.selectAll();
        //2.提取出所有的一级分类, 提取所有parentId=0的分类
        List<Category> firstList = allList.stream().filter(category -> category.getParentId() == 0).toList();
        //3.提取二级分类：一级分类的下一级>>>>>>>>>>>>>>>>
        //遍历一级分类
        firstList.forEach(first->{
            //获取一级分类的id
            Long firstId = first.getId();
            //提取所有分类中parentId=firstId的分类
            List<Category> secondList = allList.stream().filter(category -> category.getParentId() == firstId).toList();
            //添加二级分类到一级分类中
            first.setChildren(secondList);

            //4.提取三级分类：二级分类的下一级>>>>>>>>>>>>>>>>
            //遍历二级分类
            secondList.forEach(second->{
                //获取二级分类的id
                Long secondId = second.getId();
                //提取所有分类中parentId=secondId的分类
                List<Category> thirdList = allList.stream().filter(category -> category.getParentId() == secondId).toList();
                //添加三级分类到二级分类中
                second.setChildren(thirdList);
            });
        });

        //返回一级分类（包含了二级、三级）
        return firstList;
    }
}
