package com.atguigu.spzx.service;


import com.atguigu.spzx.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 商品分类 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
public interface CategoryService {

    List<Category> findNextList(long parentId);

    void exportExcel(HttpServletResponse response) throws IOException;

    List<Long> findParentList(long id);

    void importExcel(MultipartFile file) throws IOException;
}
