package com.atguigu.spzx.mapper;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 商品分类 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
@Mapper
public interface CategoryMapper{

    int countByParentId(Long id);

    List<Category> selectByParentId(long parentId);

    List<Category> selectAll();

    void insertBatch(List<CategoryExcelVo> cachedDataList);

    Long selectParentIdById(long id);
}
