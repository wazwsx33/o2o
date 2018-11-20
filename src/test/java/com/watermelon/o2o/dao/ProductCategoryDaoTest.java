package com.watermelon.o2o.dao;

import com.watermelon.o2o.BaseTest;
import com.watermelon.o2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/11/16 15:10
 */
public class ProductCategoryDaoTest extends BaseTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testQueryByShopId() throws Exception {
        long shopId = 1;
        List<ProductCategory> shopCategoryList = productCategoryDao.queryProdructCategoryList(shopId);
        System.out.println("类别数：" + shopCategoryList.size());
    }
}
