package com.atguigu.spzx.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.atguigu.spzx.mapper.CategoryMapper;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;

import java.util.List;

//不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class ExcelListener implements ReadListener<CategoryExcelVo> {

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     * 创建指定容量的ArrayList集合，避免频繁扩容，节省性能
     */
    private List<CategoryExcelVo> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    //数据访问对象
    private CategoryMapper categoryMapper;

    public ExcelListener(CategoryMapper categoryMapper){
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void invoke(CategoryExcelVo categoryExcelVo, AnalysisContext analysisContext) {
        //每读到一行内容执行一次>>>>>>>>>>>>>>

        //添加到缓存的数据中
        cachedDataList.add(categoryExcelVo);
        //如果缓存的数据有100个
        if(cachedDataList.size() == BATCH_COUNT){
            //则批量添加到数据库
            categoryMapper.insertBatch(cachedDataList);

            //重置缓存的数据
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //全部读完后执行一次>>>>>>>>>>>>>>

        //如果缓存的数据中还有元素，则继续添加到数据库
        if(cachedDataList.size() > 0){
            categoryMapper.insertBatch(cachedDataList);
        }
    }
}
