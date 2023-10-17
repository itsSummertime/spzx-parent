package com.atguigu.spzx.controller;


import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}


