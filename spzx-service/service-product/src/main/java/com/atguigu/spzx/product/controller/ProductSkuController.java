package com.atguigu.spzx.product.controller;


import com.atguigu.spzx.model.dto.h5.ProductSkuDto;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.product.service.ProductSkuService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品sku 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-24
 */
@Tag(name = "商品sku接口")
@RestController
@RequestMapping("/api/product")
public class ProductSkuController {

    @Autowired
    private ProductSkuService productSkuService;

    @Operation(summary = "分页查询")
    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<ProductSku>> findByPage(@PathVariable int page, @PathVariable int limit,
                                                   ProductSkuDto productSkuDto) {
        PageInfo<ProductSku> pageInfo = productSkuService.findByPage(page, limit, productSkuDto);
        return Result.ok(pageInfo);
    }
}

