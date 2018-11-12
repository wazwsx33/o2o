package com.watermelon.o2o.service;

import com.watermelon.o2o.dto.ShopExecution;
import com.watermelon.o2o.entity.Shop;

import java.io.File;

/**
 * @Description: 商铺 服务层
 * @Author; Watermelon
 * @Date: 2018/11/12 20:30
 */
public interface ShopService {
    ShopExecution addShop (Shop shop, File shopImg);

}
