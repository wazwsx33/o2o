package com.watermelon.o2o.controller.shopAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: 路由html跳转
 * @Author; Watermelon
 * @Date: 2018/11/13 17:25
 */

@Controller
@RequestMapping(value = "shopadmin", method = RequestMethod.GET)
public class ShopAdminController {

    @GetMapping(value = "shopoperation")
    public String shopOperation() {
        return "shop/shopoperation";
    }

    @GetMapping(value = "shoplist")
    public String shopList() {
        return "shop/shoplist";
    }

    @GetMapping(value = "shopmanagement")
    public String shopManagement() {
        return "shop/shopmanagement";
    }

    @GetMapping(value = "productcategorymanagement")
    public String productCategoryManagement() {
        return "shop//productcategorymanage";
    }

    @GetMapping(value = "productoperation")
    public String productOperation() {
        return "shop/productedit";
    }

    @GetMapping(value = "productmanagement")
    public String productManagement() {
        return "shop/productmanage";
    }
}
