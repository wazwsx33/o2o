package com.watermelon.o2o.service;

import com.watermelon.o2o.dto.ShopExecution;
import com.watermelon.o2o.entity.Shop;
import com.watermelon.o2o.exceptions.ShopOpertationException;

import java.io.InputStream;

/**
 * @Description: 商铺 服务层
 * @Author; Watermelon
 * @Date: 2018/11/12 20:30
 */
public interface ShopService {

    /**
     * 根据shopCondition分页返回响应列表数据
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

    /**
     * 通过店铺ID获取店铺信息
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId);

    /**
     * 更新店铺信息，包括图片处理
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     * @throws ShopOpertationException
     */
    ShopExecution modifyShop (Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOpertationException;

    /**
     * 注册店铺信息，包括图片处理
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     * @throws ShopOpertationException
     */
    ShopExecution addShop (Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOpertationException;

}
