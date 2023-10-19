package com.atguigu.spzx.controller;


import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 商品分类 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-14
 */
@Tag(name = "分类接口")
@RestController
@RequestMapping("/admin/product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "查询下一级分类")
    @GetMapping("/findNextList/{parentId}")
    public Result<List<Category>> findNextList(@PathVariable long parentId){
        List<Category> list = categoryService.findNextList(parentId);
        return Result.ok(list);
    }

    @Operation(summary = "导出Excel")
    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        categoryService.exportExcel(response);
    }

    @Operation(summary = "导入Excel")
    @PostMapping("/importExcel")
    public Result<T> importExcel(MultipartFile file) throws IOException {
        categoryService.importExcel(file);
        return Result.ok();
    }

    @Operation(summary = "查询上级分类id")
    @GetMapping("/findParentList/{id}")
    public Result<List<Long>> findParentList(@PathVariable long id){
        List<Long> list = categoryService.findParentList(id);
        return Result.ok(list);
    }
}


