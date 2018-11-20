package com.watermelon.o2o.service;

import com.watermelon.o2o.entity.ProductCategory;

import java.util.List;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/11/16 15:14
 */
public interface ProductCategoryService {

    /**
     * 获取某个店铺下的商品类别信息
     * @param shopId
     * @return
     */
    List<ProductCategory> getProdructCategoryList(long shopId);
}
