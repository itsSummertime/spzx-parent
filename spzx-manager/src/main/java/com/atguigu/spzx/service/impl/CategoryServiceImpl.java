package com.atguigu.spzx.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.spzx.listener.ExcelListener;
import com.atguigu.spzx.mapper.CategoryMapper;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;
import com.atguigu.spzx.service.CategoryService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品分类 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-14
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findNextList(long parentId) {
        //1.根据parent_id查询分类列表
        List<Category> list = categoryMapper.selectByParentId(parentId);

        //2.判断查到的每个分类是否还有下一级
        list.forEach(category -> {
            //根据parent_id查询分类的数量
            int count = categoryMapper.countByParentId(category.getId());
            if (count > 0) {
                //有下一级分类
                category.setHasChildren(true);
            }
        });

        return list;
    }

    @Override
    public void exportExcel(HttpServletResponse response) throws IOException {
        //1.查询所有的分类数据
        List<Category> list = categoryMapper.selectAll();

        //2.将查询到的分类数据 导出 到Excel文件中>>>>>>>>>>>>>>>>
        //2.1.将查询到的数据List<Category> 转为 List<CategoryExcelVo>
        List<CategoryExcelVo> voList = new ArrayList<>();
        //遍历数据进行转换
        list.forEach(category -> {
            CategoryExcelVo excelVo = new CategoryExcelVo();
            //复制category对象的属性 给 excelVo对象
            //注意：两个对象的属性名要一致
            BeanUtils.copyProperties(category, excelVo);

            //将复制后的对象，添加到要导出的列表中
            voList.add(excelVo);
        });

        //2.2.导出数据voList >>>
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("商品分类", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename*=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class).sheet("分类列表").doWrite(voList);
    }

    @Override
    public void importExcel(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), CategoryExcelVo.class, new ExcelListener(categoryMapper)).sheet().doRead();
    }

    @Override
    public List<Long> findParentList(long id) {
        //查询第二级分类的id
        Long secondId = categoryMapper.selectParentIdById(id);
        //查询第一级分类的id
        Long firstId = categoryMapper.selectParentIdById(secondId);

        //封装到List集合中
        List<Long> list = new ArrayList<>();
        list.add(firstId);
        list.add(secondId);
        list.add(id);
        return list;
    }
}
