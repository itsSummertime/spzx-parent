package com.atguigu.spzx.controller;


import com.atguigu.spzx.model.dto.product.CategoryBrandDto;
import com.atguigu.spzx.model.entity.product.CategoryBrand;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.service.CategoryBrandService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 分类品牌 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
@Tag(name = "分类品牌接口")
@RestController
@RequestMapping("/admin/product/categoryBrand")
public class CategoryBrandController {

    @Autowired
    private CategoryBrandService categoryBrandService;

    @Operation(summary = "分页查询")
    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<CategoryBrand>> findByPage(@PathVariable int pageNum, @PathVariable int pageSize,
                                                      @RequestBody CategoryBrandDto categoryBrandDto) {
        PageInfo<CategoryBrand> pageInfo = categoryBrandService.findByPage(pageNum, pageSize, categoryBrandDto);
        return Result.ok(pageInfo);
    }


    @Operation(summary = "添加")
    @PostMapping("/add")
    public Result<T> add(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.add(categoryBrand);
        return Result.ok();
    }

    @Operation(summary = "修改")
    @PutMapping("/update")
    public Result<T> update(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.update(categoryBrand);
        return Result.ok();
    }

    @Operation(summary = "逻辑删除")
    @DeleteMapping("/delete/{id}")
    public Result<T> delete(@PathVariable long id) {
        categoryBrandService.delete(id);
        return Result.ok();
    }

}