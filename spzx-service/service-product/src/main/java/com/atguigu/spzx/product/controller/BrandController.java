package com.atguigu.spzx.product.controller;


import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.product.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 分类品牌 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-24
 */
@Tag(name = "品牌接口")
@RestController
@RequestMapping("/api/product/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Operation(summary = "获取全部品牌")
    @GetMapping("/findAll")
    public Result<List<Brand>> findAll(){
        List<Brand> list = brandService.findAll();
        return Result.ok(list);
    }
}