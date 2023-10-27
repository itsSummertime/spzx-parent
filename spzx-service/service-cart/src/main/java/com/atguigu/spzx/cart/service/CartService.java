package com.atguigu.spzx.cart.service;

import com.atguigu.spzx.model.entity.h5.CartInfo;

import java.util.List;

public interface CartService {
    void addToCart(long skuId, int skuNum);

    List<CartInfo> cartList();

    void deleteCart(Long skuId);

    void checkCart(long skuId, int isChecked);

    void allCheckCart(int isChecked);
}
