package com.watermelon.o2o.service;

import com.watermelon.o2o.entity.ShopCategory;

import java.util.List;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/11/13 21:17
 */
public interface ShopCategoryService {

    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
