package com.atguigu.spzx.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.atguigu.spzx.model.dto.h5.ProductSkuDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.entity.product.ProductDetails;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.h5.ProductItemVo;
import com.atguigu.spzx.product.mapper.ProductDetailsMapper;
import com.atguigu.spzx.product.mapper.ProductMapper;
import com.atguigu.spzx.product.mapper.ProductSkuMapper;
import com.atguigu.spzx.product.service.ProductSkuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 商品sku 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-24
 */
@Service
public class ProductSkuServiceImpl implements ProductSkuService {
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductDetailsMapper productDetailsMapper;
    @Override
    public List<ProductSku> findHotProduct() {
        return productSkuMapper.findHotProduct();
    }

    @Override
    public PageInfo<ProductSku> findByPage(int page, int limit, ProductSkuDto productSkuDto) {
        //开启分页
        PageHelper.startPage(page, limit);
        //执行查询
        List<ProductSku> list = productSkuMapper.selectByPage(productSkuDto);
        //封装查询的结果到分页对象PageInfo
        return new PageInfo<>(list);
    }

    @Override
    public ProductItemVo item(long skuId) {
        //1.商品sku信息 > product_sku表
        ProductSku productSku = productSkuMapper.selectByID(skuId);
        //获取商品id
        Long productId = productSku.getProductId();
        //2.商品信息 > 查询product表
        Product product = productMapper.selectById(productId);
        //3.商品轮播图列表> 提取product表的slider_urls字段
        String sliderUrls = product.getSliderUrls();
        //转为List集合
        List<String> slierUrlsList = Arrays.asList(sliderUrls.split(","));
        //4.商品详情图片列表 > 查询product_details表
        ProductDetails productDetails = productDetailsMapper.selectByProductId(productId);
        //转为List集合
        String imageUrls = productDetails.getImageUrls();
        //获取图片路径
        List<String> detailsImageUrlList = Arrays.asList(imageUrls.split(","));
        //5.商品规格信息 > 提取product的字段spec_value
        String specValue = product.getSpecValue();
        //将json字符串转为json数组对象
        JSONArray specValueList = JSON.parseArray(specValue);

        //6.商品规格对应商品skuId信息 > 查询product_sku表
        List<ProductSku> skuList = productSkuMapper.selectByProductId(productId);
        // 拼接productSku(sku_spec: id)
        HashMap<String, Object> skuSpecValueMap = new HashMap<>();
        skuList.forEach(sku -> {
            skuSpecValueMap.put(sku.getSkuSpec(),sku.getId());
        });
        //封装到Vo对象
        ProductItemVo vo = new ProductItemVo();
        vo.setProductSku(productSku);
        vo.setProduct(product);
        vo.setSliderUrlList(slierUrlsList);
        vo.setDetailsImageUrlList(detailsImageUrlList);
        vo.setSpecValueList(specValueList);
        vo.setSkuSpecValueMap(skuSpecValueMap);
        return vo;
    }
}
