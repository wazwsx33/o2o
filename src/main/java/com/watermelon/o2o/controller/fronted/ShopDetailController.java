package com.watermelon.o2o.controller.fronted;

import com.watermelon.o2o.dto.ProductExecution;
import com.watermelon.o2o.entity.Product;
import com.watermelon.o2o.entity.ProductCategory;
import com.watermelon.o2o.entity.Shop;
import com.watermelon.o2o.service.ProductCategoryService;
import com.watermelon.o2o.service.ProductService;
import com.watermelon.o2o.service.ShopService;
import com.watermelon.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/12/7 15:31
 */
@Controller
@RequestMapping(value = "fronted")
public class ShopDetailController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductService productService;

    /**
     * 获取店铺信息以及该店铺下面的商品类别列表
     *
     * @param request
     * @return
     */
    @GetMapping(value = "listshopdetailpageinfo")
    @ResponseBody
    private Map<String, Object> listShopDetailPageInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        //获取前台传过来的shopId
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        Shop shop = null;
        List<ProductCategory> productCategoryList = null;
        if (shopId != -1) {
            //获取店铺ID为shopId的店铺信息
            shop = shopService.getByShopId(shopId);
            //获取店铺下面的商品类别列表
            productCategoryList = productCategoryService.getProdructCategoryList(shopId);
            modelMap.put("shop", shop);
            modelMap.put("productCategoryList", productCategoryList);
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId");
        }

        return modelMap;
    }

    /**
     * 依据查询条件分页列出该店铺下面的所有商品
     *
     * @param request
     * @return
     */
    @GetMapping(value = "listproductsbyshop")
    @ResponseBody
    private Map<String, Object> listProductsByShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        //获取页码
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        //获取每页数据量
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        //获取店铺ID
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        //空值判断
        if (pageIndex > -1 && pageSize > -1 && shopId > -1) {
            //尝试获取商品类别ID
            long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");
            //尝试获取模糊查找的商品名
            String productName = HttpServletRequestUtil.getString(request, "productName");
            Product productCondition = compactProductCondition4Search(shopId, productCategoryId, productName);
            ProductExecution pe = productService.getProductList(productCondition, pageIndex, pageSize);
            modelMap.put("productList", pe.getProductList());
            modelMap.put("count", pe.getCount());
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
        }

        return modelMap;
    }

    /**
     * 组合查询条件
     *
     * @param shopId
     * @param productCategoryId
     * @param productName
     * @return
     */
    private Product compactProductCondition4Search(long shopId, long productCategoryId, String productName) {
        Product productCondtion = new Product();
        Shop shop = new Shop();
        shop.setShopId(shopId);
        productCondtion.setShop(shop);
        if (productCategoryId != -1) {
            //查询某个商品类别下面的商品列表
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            productCondtion.setProductCategory(productCategory);
        }
        if (productName != null) {
            //查询名字里包含productName的店铺列表
            productCondtion.setProductName(productName);
        }

        //只允许选出状态为上架的商品
        productCondtion.setEnableStatus(1);

        return productCondtion;
    }
}
