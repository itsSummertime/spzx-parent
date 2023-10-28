package com.atguigu.spzx.cart.controller;


import com.atguigu.spzx.cart.service.CartService;
import com.atguigu.spzx.model.entity.h5.CartInfo;
import com.atguigu.spzx.model.vo.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "购物车接口")
@RestController
@RequestMapping("api/order/cart")
public class CartController{
    @Autowired
    private CartService cartService;

    @Operation(summary = "添加购物车")
    @GetMapping("/auth/addToCart/{skuId}/{skuNum}")
    public Result<T> AddToCart(@PathVariable long skuId, @PathVariable int skuNum){
        cartService.addToCart(skuId,skuNum);
        return Result.ok();
    }
    @Operation(summary = "查询购物车")
    @GetMapping("/auth/cartList")
    public Result<List<CartInfo>> cartList() {
        List<CartInfo> list = cartService.cartList();
        return Result.ok(list);
    }

    @Operation(summary = "删除购物车商品")
    @DeleteMapping ("/auth/deleteCart/{skuId}")
    public Result<T> deleteCart(@PathVariable Long skuId) {
        cartService.deleteCart(skuId);
        return Result.ok();
    }
    @Operation(summary = "更新购物车商品选中状态")
    @GetMapping("/auth/checkCart/{skuId}/{isChecked}")
    public Result<T> checkCart(@PathVariable long skuId, @PathVariable int isChecked) {
        cartService.checkCart(skuId, isChecked);
        return Result.ok();
    }


    @Operation(summary = "更新购物车商品全部选中状态")
    @GetMapping("/auth/allCheckCart/{isChecked}")
    public Result<T> allCheckCart(@PathVariable int isChecked) {
        cartService.allCheckCart(isChecked);
        return Result.ok();
    }
    @Operation(summary = "清空购物车")
    @GetMapping("/auth/clearCart")
    public Result<T> clearCart() {
        cartService.clearCart();
        return Result.ok();
    }

    @Operation(summary = "查询购物车已选的商品(OpenFeign)")
    @GetMapping("/auth/getChecked")
    public List<CartInfo> getChecked() {
        return cartService.getChecked();
    }
}
