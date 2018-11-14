package com.watermelon.o2o.dao;

import com.watermelon.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 商铺类型 接口类
 * @Author; Watermelon
 * @Date: 2018/11/13 20:29
 */
public interface ShopCategoryDao {
    List<ShopCategory> quesyShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}
