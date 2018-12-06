package com.watermelon.o2o.controller.fronted;

import com.watermelon.o2o.dto.ShopExecution;
import com.watermelon.o2o.entity.Area;
import com.watermelon.o2o.entity.Shop;
import com.watermelon.o2o.entity.ShopCategory;
import com.watermelon.o2o.service.AreaService;
import com.watermelon.o2o.service.ShopCategoryService;
import com.watermelon.o2o.service.ShopService;
import com.watermelon.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/12/6 15:35
 */

@Controller
@RequestMapping(value = "fronted", method = RequestMethod.GET)
public class ShopListController {

    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private ShopService shopService;

    /**
     * 返回商品列表页里的ShopCategory列表（二级或一级），以及区域信息列表
     *
     * @param request
     * @return
     */
    @GetMapping(value = "listshopspageinfo")
    @ResponseBody
    private Map<String, Object> listShopsPageInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        //试着从前端请求中获取parentId
        long parentId = HttpServletRequestUtil.getLong(request, "parentId");
        List<ShopCategory> shopCategoryList = null;

        if (parentId != -1) {
            //如果parentId存在，则取出该一级ShopCategory下的二级列表
            try {
                ShopCategory shopCategoryCondition = new ShopCategory();
                ShopCategory parent = new ShopCategory();
                parent.setShopCategoryId(parentId);
                shopCategoryCondition.setParent(parent);
                shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
        } else {
            try {
                //如果parentId不存在，则去除所有一级ShopCategory（用户在首页选择的是全部商品列表）
                shopCategoryList = shopCategoryService.getShopCategoryList(null);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
        }

        modelMap.put("shopCategoryList", shopCategoryList);
        List<Area> areaList = null;
        try {
            //获取区域列表信息
            areaList = areaService.getAreaList();
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);

            return modelMap;
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }

        return modelMap;
    }

    @GetMapping(value = "listshops")
    @ResponseBody
    private Map<String, Object> listShops(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        //获取页码
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        //获取每页数据量
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        //非空判断
        if (pageIndex > -1 && pageSize > -1) {
            //获取一级类别ID
            long parentId = HttpServletRequestUtil.getLong(request, "parentId");
            //获取特定二级类别ID
            long shopCategoryId = HttpServletRequestUtil.getLong(request, "shopCategoryId");
            //获取区域ID
            int areaId = HttpServletRequestUtil.getInt(request, "areaId");
            //获取模糊查询名字
            String shopName = HttpServletRequestUtil.getString(request, "shopName");
            //获取组合之后的查询条件
            Shop shopCondition = compactShopCondition4Search(parentId, shopCategoryId, areaId, shopName);
            //根据查询条件和分页信息获取店铺列表，并返回总数
            ShopExecution se = shopService.getShopList(shopCondition, pageIndex, pageSize);
            modelMap.put("shopList", se.getShopList());
            modelMap.put("count", se.getCount());
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex");
        }

        return modelMap;
    }

    /**
     * 组合查询条件
     *
     * @param parentId
     * @param shopCategoryId
     * @param areaId
     * @param shopName
     * @return
     */
    private Shop compactShopCondition4Search(long parentId, long shopCategoryId, int areaId, String shopName) {
        Shop shopCondition = new Shop();
        if (parentId != -1) {
            //查询某个一级ShopCategory下面的所有二级类别里面的店铺列表
            ShopCategory childCategory = new ShopCategory();
            ShopCategory parentCategory = new ShopCategory();
            parentCategory.setShopCategoryId(parentId);
            childCategory.setParent(parentCategory);
            shopCondition.setShopCategory(childCategory);
        }
        if (shopCategoryId != -1L) {
            //查询某个二级类别下面的店铺列表
            ShopCategory shopCategory = new ShopCategory();
            shopCategory.setShopCategoryId(shopCategoryId);
            shopCondition.setShopCategory(shopCategory);
        }
        if (areaId != -1L) {
            //查询位于某个区域ID下的店铺列表
            Area area = new Area();
            area.setAreaId(areaId);
            shopCondition.setArea(area);
        }

        if (shopName != null) {
            //查询名字里包含shopName的店铺列表
            shopCondition.setShopName(shopName);
        }
        //前端展示的店铺都是审核成功的店铺
        shopCondition.setEnableStatus(1);

        return shopCondition;
    }
}
