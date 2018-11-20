package com.watermelon.o2o.service.impl;

import com.watermelon.o2o.dao.ProductCategoryDao;
import com.watermelon.o2o.dto.ProductCategoryExecution;
import com.watermelon.o2o.entity.ProductCategory;
import com.watermelon.o2o.enums.ProductCategoryStateEnum;
import com.watermelon.o2o.exceptions.ProductCategoryOperationException;
import com.watermelon.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/11/16 15:15
 */

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> getProdructCategoryList(long shopId) {
        return productCategoryDao.queryProdructCategoryList(shopId);
    }

    @Override
    @Transactional
    public ProductCategoryExecution batchAddProductCategoryList(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effectedNum <= 0){
                    throw new ProductCategoryOperationException("店铺类别创建失败");
                }else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationException("batchAddProductCategory error:" + e.getMessage());
            }
        }else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }
}
