package com.watermelon.o2o.service.impl;

import com.watermelon.o2o.dao.ShopDao;
import com.watermelon.o2o.dto.ShopExecution;
import com.watermelon.o2o.entity.Shop;
import com.watermelon.o2o.enums.ShopStateEnum;
import com.watermelon.o2o.exceptions.ShopOpertationException;
import com.watermelon.o2o.service.ShopService;
import com.watermelon.o2o.util.ImageUtil;
import com.watermelon.o2o.util.PageCalculator;
import com.watermelon.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

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
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOpertationException {

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
            } else {
                if (shopImgInputStream != null) {
                    //存储图片
                    try {
                        addShopImg(shop, shopImgInputStream, fileName);
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
     *
     * @param shop
     * @param shopImgInputStream
     */
    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);

        shop.setShopImg(shopImgAddr);
    }

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOpertationException {

        //判断是否合法
        if (shop == null || shop.getShopId() == null)
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        else {
            try {
                //1、判断是否需要处理图片
                if (shopImgInputStream != null && fileName != null && !"".equals(fileName)) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    //先删除图片再上传新图片
                    if (tempShop.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    addShopImg(shop, shopImgInputStream, fileName);
                }
                //2、更新店铺信息
                shop.setLastEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0)
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                else {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            } catch (Exception e) {
                throw new ShopOpertationException("modifyShop error:" + e.getMessage());
            }
        }
    }

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {

        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        int count = shopDao.queryShopCount(shopCondition);

        ShopExecution se = new ShopExecution();

        if (shopList != null) {
            se.setShopList(shopList);
            se.setCount(count);
        } else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }

        return se;

    }
}
