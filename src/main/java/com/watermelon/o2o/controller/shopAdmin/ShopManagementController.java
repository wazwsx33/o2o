package com.watermelon.o2o.controller.shopAdmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.watermelon.o2o.dto.ShopExecution;
import com.watermelon.o2o.entity.Area;
import com.watermelon.o2o.entity.PersonInfo;
import com.watermelon.o2o.entity.Shop;
import com.watermelon.o2o.entity.ShopCategory;
import com.watermelon.o2o.enums.ShopStateEnum;
import com.watermelon.o2o.exceptions.ShopOpertationException;
import com.watermelon.o2o.service.AreaService;
import com.watermelon.o2o.service.ShopCategoryService;
import com.watermelon.o2o.service.ShopService;
import com.watermelon.o2o.util.CodeUtil;
import com.watermelon.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 店铺管理
 * @Author; Watermelon
 * @Date: 2018/11/13 15:56
 */

@Controller
@RequestMapping(value = "shop")
public class ShopManagementController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;

    /**
     * 注册商铺
     * @param request
     * @return
     */
    @PostMapping(value = "registershop")
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<>();

        if (!CodeUtil.checkVerifyCode(request)){
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");

            return modelMap;
        }

        //1、接受并转化相应的参数，包括商铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;

        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());

            return modelMap;
        }

        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        //判断是否有文件上传流
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");

            return modelMap;
        }
        //2、注册商铺
        if (shop != null && shopImg != null) {
            //假定前台信息不可靠，尽可能少依靠前台传值
            PersonInfo owner = new PersonInfo();
            //Session TODO
            owner.setUserId(1L);
            shop.setOwner(owner);

            ShopExecution se;
            try {
                se = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (ShopOpertationException | IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }

            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");

            return modelMap;
        }

    }

    /**
     * 获取注册商铺页面的初始化信息
     * @return
     */
    @GetMapping(value = "getshopinitinfo")
    @ResponseBody
    private Map<String, Object> getshopinitinfo() {
        Map<String, Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        List<Area> areaList = new ArrayList<>();

        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();

            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);

        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }

        return modelMap;
    }
}
