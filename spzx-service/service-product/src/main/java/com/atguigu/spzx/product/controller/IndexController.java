package com.atguigu.spzx.product.controller;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.h5.IndexVo;
import com.atguigu.spzx.product.service.CategoryService;
import com.atguigu.spzx.product.service.ProductSkuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(name = "首页接口")
@RestController
@RequestMapping("api/product")
public class IndexController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductSkuService productSkuService;

    @Operation(summary = "获取首页数据")
    @GetMapping("/index")
    public Result<IndexVo> index() {
        //查询一级分类
        List<Category> categoryList = categoryService.findFistCategory();
        //查询畅销商品
        List<ProductSku> productSkuList = productSkuService.findHotProduct();
        //封装数据
        IndexVo indexVo = new IndexVo();
        indexVo.setCategoryList(categoryList);
        indexVo.setProductSkuList(productSkuList);
        return Result.ok(indexVo);
    }
}
