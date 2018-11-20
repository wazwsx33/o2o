package com.watermelon.o2o.controller.shopAdmin;

import com.watermelon.o2o.dto.Result;
import com.watermelon.o2o.entity.ProductCategory;
import com.watermelon.o2o.entity.Shop;
import com.watermelon.o2o.enums.ProductCategoryStateEnum;
import com.watermelon.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
}
