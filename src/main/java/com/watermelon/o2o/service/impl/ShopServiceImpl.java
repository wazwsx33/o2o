package com.watermelon.o2o.service.impl;

import com.watermelon.o2o.dao.ShopDao;
import com.watermelon.o2o.dto.ShopExecution;
import com.watermelon.o2o.entity.Shop;
import com.watermelon.o2o.enums.ShopStateEnum;
import com.watermelon.o2o.exceptions.ShopOpertationException;
import com.watermelon.o2o.service.ShopService;
import com.watermelon.o2o.util.ImageUtil;
import com.watermelon.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;

/**
 * @Description: 商铺 服务层实现类
 * @Author; Watermelon
 * @Date: 2018/11/12 20:32
 */

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, File shopImg) {

        //空值判断
        if (shop == null)
            return new ShopExecution(ShopStateEnum.NULL_SHOP);

        try {
            //赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());

            //添加商铺信息
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                throw new ShopOpertationException("商铺创建失败");  //只有抛出ShopOpertationException时事务才会终止
            }else {
                if (shopImg != null){
                    //存储图片
                    try {
                        addShopImg(shop, shopImg);
                    } catch (Exception e) {
                        throw new ShopOpertationException("addShopImg error:" + e.getMessage());
                    }
                    //更新商铺图片地址
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0)
                        throw new ShopOpertationException("更新图片地址失败");
                }

            }

        } catch (Exception e) {
            throw new ShopOpertationException("addShop error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    /**
     * 添加商铺图片
     * @param shop
     * @param shopImg
     */
    private void addShopImg(Shop shop, File shopImg) {
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
        
        shop.setShopImg(shopImgAddr);
    }


}
