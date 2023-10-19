package com.atguigu.spzx.controller;


import com.atguigu.spzx.model.entity.base.ProductUnit;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.service.ProductUnitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品单位 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-17
 */
@Tag(name = "商品单位接口")
@RestController
@RequestMapping("/admin/product/productUnit")
public class ProductUnitController {

    @Autowired
    private ProductUnitService productUnitService;

    @Operation(summary = "查询所有")
    @GetMapping("/findAll")
    public Result<List<ProductUnit>> findAll(){
        List<ProductUnit> list = productUnitService.findAll();
        return Result.ok(list);
    }
}


