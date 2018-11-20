package com.watermelon.o2o.controller.shopAdmin;

import com.watermelon.o2o.dto.ProductCategoryExecution;
import com.watermelon.o2o.dto.Result;
import com.watermelon.o2o.entity.ProductCategory;
import com.watermelon.o2o.entity.Shop;
import com.watermelon.o2o.enums.ProductCategoryStateEnum;
import com.watermelon.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/11/16 15:17
 */

@Controller
@RequestMapping(value = "shopadmin")
public class ProductCategoryManagementController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping(value = "getproductcategorylist")
    @ResponseBody
    private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {

        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        List<ProductCategory> list = null;

        if (currentShop != null && currentShop.getShopId() > 0) {
            list = productCategoryService.getProdructCategoryList(currentShop.getShopId());
            return new Result<>(true, list);
        } else {
            ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<>(false, ps.getState(), ps.getStateInfo());
        }
    }

    @PostMapping(value = "addproductcategorys")
    @ResponseBody
    private Map<String, Object> addproductcategorys(@RequestBody List<ProductCategory> productCategoryList, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");

        for (ProductCategory pc : productCategoryList){
            pc.setShopId(currentShop.getShopId());
        }

        if (productCategoryList != null && productCategoryList.size() > 0){
            try {
                ProductCategoryExecution pe = productCategoryService.batchAddProductCategoryList(productCategoryList);
                if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()){
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());

                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请至少输入一个商品类别");
        }

        return modelMap;
    }
}
