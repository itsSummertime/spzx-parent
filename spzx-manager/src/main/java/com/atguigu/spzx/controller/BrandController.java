package com.atguigu.spzx.controller;


import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.service.BrandService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 分类品牌 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-14
 */
@Tag(name = "品牌接口")
@RestController
@RequestMapping("/admin/product/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Operation(summary = "分页查询")
    @GetMapping("/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<Brand>> findByPage(@PathVariable int pageNum, @PathVariable int pageSize){
        PageInfo<Brand> pageInfo = brandService.findByPage(pageNum, pageSize);
        return Result.ok(pageInfo);
    }
    @Operation(summary = "添加")
    @PostMapping("/add")
    public Result<T> add(@RequestBody Brand brand){
        brandService.add(brand);
        return Result.ok();
    }

    @Operation(summary = "修改")
    @PutMapping("/update")
    public Result<T> update(@RequestBody Brand brand){
        brandService.update(brand);
        return Result.ok();
    }

    @Operation(summary = "逻辑删除")
    @DeleteMapping("/delete/{id}")
    public Result<T> delete(@PathVariable long id){
        brandService.delete(id);
        return Result.ok();
    }

    @Operation(summary = "查询所有")
    @GetMapping("/findAll")
    public Result<List<Brand>> findAll(){
        List<Brand> list = brandService.findAll();
        return Result.ok(list);
    }
}


