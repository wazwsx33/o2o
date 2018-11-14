package com.watermelon.o2o.service.impl;

import com.watermelon.o2o.dao.ShopCategoryDao;
import com.watermelon.o2o.entity.ShopCategory;
import com.watermelon.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/11/13 21:18
 */

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryDao.quesyShopCategory(shopCategoryCondition);
    }
}
