package com.atguigu.spzx.controller;


import com.atguigu.spzx.model.entity.product.ProductSpec;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.service.ProductSpecService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品规格 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
@Tag(name = "商品规格接口")
@RestController
@RequestMapping("/admin/product/productSpec")
public class ProductSpecController {

    @Autowired
    private ProductSpecService productSpecService;

    @Operation(summary = "分页查询")
    @GetMapping("/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<ProductSpec>> findByPage(@PathVariable int pageNum, @PathVariable int pageSize){
        PageInfo<ProductSpec> pageInfo = productSpecService.findByPage(pageNum, pageSize);
        return Result.ok(pageInfo);
    }

    @Operation(summary = "添加")
    @PostMapping("/add")
    public Result<T> add(@RequestBody ProductSpec productSpec){
        productSpecService.add(productSpec);
        return Result.ok();
    }

    @Operation(summary = "修改")
    @PutMapping("/update")
    public Result<T> update(@RequestBody ProductSpec productSpec){
        productSpecService.update(productSpec);
        return Result.ok();
    }

    @Operation(summary = "逻辑删除")
    @DeleteMapping("/delete/{id}")
    public Result<T> delete(@PathVariable long id){
        productSpecService.delete(id);
        return Result.ok();
    }
    @Operation(summary = "查询所有")
    @GetMapping("/findAll")
    public Result<List<ProductSpec>> findAll(){
        List<ProductSpec> list = productSpecService.findAll();
        return Result.ok(list);
    }
}

