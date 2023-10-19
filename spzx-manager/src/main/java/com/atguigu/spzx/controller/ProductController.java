package com.atguigu.spzx.controller;


import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.service.ProductService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
@Tag(name = "商品接口")
@RestController
@RequestMapping("/admin/product/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "分页查询")
    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<Product>> findByPage(@PathVariable int pageNum, @PathVariable int pageSize,
                                                @RequestBody ProductDto productDto) {
        PageInfo<Product> pageInfo = productService.findByPage(pageNum, pageSize, productDto);
        return Result.ok(pageInfo);
    }

    @Operation(summary = "添加")
    @PostMapping("/add")
    public Result<T> add(@RequestBody Product product) {
        productService.add(product);
        return Result.ok();
    }

    @Operation(summary = "查询详情")
    @GetMapping("/findById/{id}")
    public Result<Product> findById(@PathVariable long id) {
        Product product = productService.findById(id);
        return Result.ok(product);
    }

    @Operation(summary = "修改")
    @PutMapping("/update")
    public Result<T> update(@RequestBody Product product) {
        productService.update(product);
        return Result.ok();
    }


    @Operation(summary = "逻辑删除")
    @DeleteMapping("/delete/{id}")
    public Result<T> delete(@PathVariable long id) {
        productService.delete(id);
        return Result.ok();
    }

    @Operation(summary = "审核")
    @PutMapping("/updateAuditStatus/{id}/{auditStatus}")
    public Result<T> updateAuditStatus(@PathVariable long id, @PathVariable int auditStatus) {
        productService.updateAuditStatus(id, auditStatus);
        return Result.ok();
    }

    @Operation(summary = "上架与下架")
    @PutMapping("/updateStatus/{id}/{status}")
    public Result<T> updateStatus(@PathVariable long id, @PathVariable int status) {
        productService.updateStatus(id, status);
        return Result.ok();
    }

}