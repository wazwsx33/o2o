package com.watermelon.o2o.controller.shopAdmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.watermelon.o2o.dto.ImageHolder;
import com.watermelon.o2o.dto.ProductExecution;
import com.watermelon.o2o.entity.Product;
import com.watermelon.o2o.entity.ProductCategory;
import com.watermelon.o2o.entity.Shop;
import com.watermelon.o2o.enums.ProductStateEnum;
import com.watermelon.o2o.exceptions.ProductOperationException;
import com.watermelon.o2o.service.ProductService;
import com.watermelon.o2o.util.CodeUtil;
import com.watermelon.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/11/23 16:13
 */
@Controller
@RequestMapping(value = "shopadmin")
public class ProductManagementController {

    @Autowired
    private ProductService productService;

    //支持上传商品详情图的最大数量
    private static final int IMAGEMAXCOUNT = 6;

    @PostMapping(value = "addproduct")
    private Map<String, Object> addProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        //验证码校验
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");

            return modelMap;
        }

        //接受前端参数的变量的初始化，包括商品、缩略图、详情图列表实体类
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        String productStr = HttpServletRequestUtil.getString(request, "productStr");
        //处理文件流
        MultipartHttpServletRequest multipartHttpServletRequest = null;
        ImageHolder thumbnail = null;
        List<ImageHolder> productImgList = new ArrayList<>();
        //获取文件流
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        try {
            //若请求中存在文件流，则取出相关的文件（包括缩略图和详情图）
            if (multipartResolver.isMultipart(request)) {
                multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                //取出缩略图并构建ImageHolder对象
                CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("thumbnail");
                thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(), thumbnailFile.getInputStream());
                //去除详情图列表并构建List<ImageHolder>列表对象，最多支持六张图片上传
                for (int i = 0; i < IMAGEMAXCOUNT; i++) {
                    CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("productImg" + i);
                    if (productImgFile != null) {
                        //若去除的第i个详情图片文件流不为空，则将其加入详情图列表
                        ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(), productImgFile.getInputStream());
                        productImgList.add(productImg);
                    } else {
                        //若取出的第i个详情图片文件流为空，则终止循环
                        break;
                    }
                }
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "上传图片不能为空");
            }
        } catch (Exception e) {
            modelMap.put("success",false);
            modelMap.put("errMsg", e.toString());

            return modelMap;
        }

        try {
            //尝试获取前端传过来的表单String流并将其转换成Product实体类
            product = mapper.readValue(productStr, Product.class);
        } catch (Exception e) {
            modelMap.put("success",false);
            modelMap.put("errMsg", e.toString());

            return modelMap;
        }

        //若Product信息，缩略图以及详情图列表为非空，则开始进行商品添加操作
        if(product != null && thumbnail != null && productImgList.size() > 0) {
            try {
                //从session中获取当前店铺的ID并赋值给product，减少对前端数据的依赖
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                Shop shop = new Shop();
                shop.setShopId(currentShop.getShopId());
                product.setShop(shop);
                //执行添加操作
                ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
                if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            } catch (ProductOperationException e) {
                modelMap.put("success",false);
                modelMap.put("errMsg", e.toString());

                return modelMap;
            }
        } else {
            modelMap.put("success",false);
            modelMap.put("errMsg", "请输入商品信息");
        }
        return modelMap;
    }

/*    @GetMapping(value = "getproductbyid")
    @ResponseBody
    private Map<String, Object> getProductById(@RequestParam Long productId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (productId > -1) {
            Product product = productService.getProductById(productId);
            List<ProductCategory> productCategoryList = productCategoryService
                    .getByShopId(product.getShop().getShopId());
            modelMap.put("product", product);
            modelMap.put("productCategoryList", productCategoryList);
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
        }
        return modelMap;
    }*/
}
