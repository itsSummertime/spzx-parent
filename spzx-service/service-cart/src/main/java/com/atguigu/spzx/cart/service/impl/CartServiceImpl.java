package com.atguigu.spzx.cart.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.cart.service.CartService;
import com.atguigu.spzx.common.AuthContextUtil;
import com.atguigu.spzx.feign.product.ProductFeignClient;
import com.atguigu.spzx.model.entity.h5.CartInfo;
import com.atguigu.spzx.model.entity.product.ProductSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductFeignClient productFeignClient;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void addToCart(long skuId, int skuNum) {
        //从线程变量中获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();

        //判断当前用户的购物车中对应的商品是否存在
        String str = (String) redisTemplate.opsForHash().get("user:cart:" + userId, String.valueOf(skuId));
        CartInfo cartInfo;
        if (StringUtils.hasText(str)) {
            //已存在，则更新数量 >>>>>>>>>>>>>>>
            //json字符串转为CartInfo
            cartInfo = JSON.parseObject(str, CartInfo.class);
            //更新数量、已选、时间
            cartInfo.setSkuNum(cartInfo.getSkuNum() + skuNum);
            cartInfo.setIsChecked(1);
            cartInfo.setUpdateTime(new Date());
        } else {
            //不存在，则添加 >>>>>>>>>>>>>>>
            //OpenFeign远程调用商品服务，获取商品sku信息
            ProductSku productSku = productFeignClient.findById(skuId);

            //提取需要保存到Redis中的购物车信息
            cartInfo = new CartInfo();
            cartInfo.setUserId(userId);
            cartInfo.setSkuId(skuId);
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setSkuNum(skuNum);
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
            cartInfo.setIsChecked(1);
            cartInfo.setCreateTime(new Date());
            cartInfo.setUpdateTime(new Date());
        }

        //购物车对象转为json字符串
        String jsonString = JSON.toJSONString(cartInfo);
        //保存到Redis,使用Hash类型保存
        redisTemplate.opsForHash().put("user:cart:" + userId, String.valueOf(skuId), jsonString);
    }

    @Override
    public List<CartInfo> cartList() {
        //从线程变量中获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();

        //获取Redis中该用户的购物车列表
        List<Object> list = redisTemplate.opsForHash().values("user:cart:" + userId);
        if(!CollectionUtils.isEmpty(list)){
            //不为空,则将List<Object>转为List<CartInfo>
            return list.stream().map(obj -> JSON.parseObject(obj.toString(),CartInfo.class)) //提取每个Object转为CartInfo
                    .sorted((c1,c2) -> c2.getCreateTime().compareTo(c1.getCreateTime())) //按照createTime降序排列
                    .toList(); //保存到另一个List
        }
        //为空,返回一个空集合
        return new ArrayList<>();
    }

    @Override
    public void deleteCart(Long skuId) {
        //从线程变量中获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();
        //删除Redis购物车中对应的商品
        redisTemplate.opsForHash().delete("user:cart:" + userId,String.valueOf(skuId));
    }

    @Override
    public void checkCart(long skuId, int isChecked) {
        //从线程变量中获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();

        //获取购物车中对应的商品
        String str = (String) redisTemplate.opsForHash().get("user:cart:" + userId,String.valueOf(skuId));

        //转化为CartInfo
        CartInfo cartInfo = JSON.parseObject(str,CartInfo.class);
        //修改选中状态,更新时间
        cartInfo.setIsChecked(isChecked);
        cartInfo.setUpdateTime(new Date());


        //购物车对象转为json字符串
        String jsonString = JSON.toJSONString(cartInfo);
        //保存到Redis,使用Hash类型保存
        redisTemplate.opsForHash().put("user:cart:" + userId, String.valueOf(skuId), jsonString);
    }

    @Override
    public void allCheckCart(int isChecked) {
        //从线程变量中获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();
        //获取Redis中该用户的购物车列表
        List<Object> list = redisTemplate.opsForHash().values("user:cart:" + userId);

        //遍历 List<Object>
        list.forEach(obj -> {
            //每个Object转为CartInfo
            CartInfo cartInfo = JSON.parseObject(obj.toString(), CartInfo.class);
            //修改选中状态、更新时间
            cartInfo.setIsChecked(isChecked);
            cartInfo.setUpdateTime(new Date());

            //购物车对象转为json字符串
            String jsonString = JSON.toJSONString(cartInfo);
            //保存到Redis,使用Hash类型保存
            redisTemplate.opsForHash().put("user:cart:" + userId, String.valueOf(cartInfo.getSkuId()), jsonString);
        });
    }

    @Override
    public void clearCart() {
        //从线程变量中获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();
        //删除Redis中该用户的购物车
        redisTemplate.delete("user:cart:" + userId);
    }

    @Override
    public List<CartInfo> getChecked() {
        //从线程变量中获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();
        //获取Redis中该用户的购物车列表
        List<Object> list = redisTemplate.opsForHash().values("user:cart:" + userId);
        //提取出购物车列表中已选的
        return list.stream().map(obj -> JSON.parseObject(obj.toString(), CartInfo.class)) //将每个Object转为CartInfo
                .filter(cartInfo -> cartInfo.getIsChecked() == 1) //过滤出已选中的
                .toList(); //存入另一个List

    }

    @Override
    public void deleteChecked() {
        //从线程变量中获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();
        //获取Redis中该用户的购物车列表
        List<Object> list = redisTemplate.opsForHash().values("user:cart:"+userId);
        //遍历购物车列表
        list.forEach(obj -> {
            //Object转为CartInfo
            CartInfo cartInfo = JSON.parseObject(obj.toString(),CartInfo.class);
            if (cartInfo.getIsChecked() == 1) {
                //删除已选择的
                redisTemplate.opsForHash().delete("user:cart:"+userId,String.valueOf(cartInfo.getSkuId()));
            }
        });
    }
}
