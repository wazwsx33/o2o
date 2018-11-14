package com.watermelon.o2o.controller.shopAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: 店铺管理
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
}
