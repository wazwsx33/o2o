package com.watermelon.o2o.dao;

import com.watermelon.o2o.entity.Shop;

/**
 * @Description: 商铺 接口类
 * @Author; Watermelon
 * @Date: 2018/11/12 14:36
 */
public interface ShopDao {

    /**
     * 新增商铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
