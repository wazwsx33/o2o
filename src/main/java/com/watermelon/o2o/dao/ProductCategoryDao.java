package com.watermelon.o2o.dao;

import com.watermelon.o2o.entity.ProductCategory;

import java.util.List;

/**
 * @Description: 商品分类 接口类
 * @Author; Watermelon
 * @Date: 2018/11/16 14:59
 */
public interface ProductCategoryDao {

    /**
     * 通过 shop id 查询店铺商品类别
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProdructCategoryList(long shopId);

    /**
     * 批量新增商品类别
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);
}
