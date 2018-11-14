package com.watermelon.o2o.service;

import com.watermelon.o2o.BaseTest;
import com.watermelon.o2o.dto.ShopExecution;
import com.watermelon.o2o.entity.Area;
import com.watermelon.o2o.entity.PersonInfo;
import com.watermelon.o2o.entity.Shop;
import com.watermelon.o2o.entity.ShopCategory;
import com.watermelon.o2o.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @Description: ShopService 测试类
 * @Author; Watermelon
 * @Date: 2018/11/12 20:59
 */
public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        owner.setUserId(1L);
        area.setAreaId(1);
        shopCategory.setShopCategoryId(1L);

        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);

        shop.setShopName("测试的店铺3");
        shop.setShopDesc("test3");
        shop.setShopAddr("test3");
        shop.setPhone("test3");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");

        File shopImg = new File("F:/timg.jpg");
        InputStream inputStream = new FileInputStream(shopImg);
        ShopExecution se = shopService.addShop(shop, inputStream, shopImg.getName());

        assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
    }
}
