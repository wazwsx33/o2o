package com.watermelon.o2o.controller.fronted;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/11/30 17:05
 */

@Controller
@RequestMapping(value = "fronted")
public class FrontedController {

    @GetMapping(value = "index")
    private String index() {
        return "fronted/index";
    }

    @GetMapping(value = "shoplist")
    private String showShopList() {
        return "fronted/shoplist";
    }

    @GetMapping(value = "shopdetail")
    private String showShopDetail() {
        return "fronted/shopdetail";
    }

    @GetMapping(value = "productdetail")
    private String showProductDetail() {
        return "fronted/productdetail";
    }
}
