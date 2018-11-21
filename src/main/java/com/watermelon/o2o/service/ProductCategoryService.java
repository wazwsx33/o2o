package com.watermelon.o2o.service;

import com.watermelon.o2o.dto.ProductCategoryExecution;
import com.watermelon.o2o.entity.ProductCategory;
import com.watermelon.o2o.exceptions.ProductCategoryOperationException;

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

    /**
     * 批量添加商品类别
     * @param productCategoryList
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution batchAddProductCategoryList(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;

    /**
     * 将此类别下的商品里的类别ID置为空，再删除掉商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException;
}
