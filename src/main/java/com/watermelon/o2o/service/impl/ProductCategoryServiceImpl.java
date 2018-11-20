package com.watermelon.o2o.service.impl;

import com.watermelon.o2o.dao.ProductCategoryDao;
import com.watermelon.o2o.entity.ProductCategory;
import com.watermelon.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
