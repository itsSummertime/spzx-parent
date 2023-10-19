package com.atguigu.spzx.mapper;

import com.atguigu.spzx.model.entity.base.ProductUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 商品单位 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-17
 */
@Mapper
public interface ProductUnitMapper {

    List<ProductUnit> findAll();
}