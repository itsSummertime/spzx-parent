package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.mapper.ProductDetailsMapper;
import com.atguigu.spzx.mapper.ProductMapper;
import com.atguigu.spzx.mapper.ProductSkuMapper;
import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.entity.product.ProductDetails;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;


    @Override
    public PageInfo<Product> findByPage(int pageNum, int pageSize, ProductDto productDto) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        //执行查询
        List<Product> list = productMapper.selectByPage(productDto);
        //将查询结果封装到分页对象PageInfo
        return new PageInfo<>(list);
    }

    @Override
    public void add(Product product) {
        //1.添加spu数据  product表>>>>>>>>>>>>>>>>>>>>>>>>>
        productMapper.insert(product);
        //添加商品后，获取商品的id
        Long productId = product.getId();

        //2.添加sku数据  product_sku表>>>>>>>>>>>>>>>>>>>>>>>>>
        //遍历得到所有的sku数据
        List<ProductSku> skuList = product.getProductSkuList();
        for (int i = 0; i < skuList.size(); i++) {
            ProductSku productSku = skuList.get(i);
            //设置商品id
            productSku.setProductId(productId);
            //设置sku_code
            productSku.setSkuCode(productId + "_" + i);
            //设置sku_name
            productSku.setSkuName(product.getName() + productSku.getSkuSpec());
        }
        //批量添加sku数据
        productSkuMapper.insertBatch(skuList);

        //3.添加详情图片 product_details表>>>>>>>>>>>>>>>>>>>>>>>>>
        //创建详情图片对应的实体类
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(productId); //商品id
        productDetails.setImageUrls(product.getDetailsImageUrls()); //图片地址
        productDetailsMapper.insert(productDetails);
    }

    @Override
    public Product findById(long id) {
        //1.查询spu数据、品牌名、分类名
        Product product = productMapper.selectById(id);
        //2.查询sku数据
        List<ProductSku> skuList = productSkuMapper.selectByProductId(id);
        //3.查询详情图片
        ProductDetails productDetails = productDetailsMapper.selectByProductId(id);

        //封装数据到product
        product.setProductSkuList(skuList); //sku数据
        product.setDetailsImageUrls(productDetails.getImageUrls()); //详情图片
        return product;
    }

    @Override
    public void update(Product product) {
        //1.修改spu数据  product表>>>>>>>>>>>>>>>>>>
        productMapper.update(product);

        //2.修改sku数据  product_sku表>>>>>>>>>>>>>>>>>>
        //遍历获取所有的sku
        List<ProductSku> skuList = product.getProductSkuList();
        skuList.forEach(productSku -> {
            //遍历一个sku，就修改一个
            productSkuMapper.update(productSku);
        });

        //3.修改详情图片 product_details表>>>>>>>>>>>>>>>>>>
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId()); //商品id
        productDetails.setImageUrls(product.getDetailsImageUrls()); //图片地址
        productDetailsMapper.update(productDetails);
    }

    @Override
    public void delete(long id) {
        //1.删除spu数据    product表>>>>>>>>>>>>>>>>
        productMapper.delete(id);
        //2.删除sku数据    product_sku表>>>>>>>>>>>>
        productSkuMapper.deleteByProductId(id);
        //3.删除详情图片    product_details表>>>>>>>>>>>>
        productDetailsMapper.deleteByProductId(id);
    }

    @Override
    public void updateAuditStatus(long id, int auditStatus) {
        productMapper.updateAuditStatus(id, auditStatus);
    }

    @Override
    public void updateStatus(long id, int status) {
        productMapper.updateStatus(id, status);
    }
}
