package com.watermelon.o2o.controller.fronted;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.watermelon.o2o.entity.PersonInfo;
import com.watermelon.o2o.entity.Product;
import com.watermelon.o2o.service.ProductService;
import com.watermelon.o2o.util.HttpServletRequestUtil;
import com.watermelon.o2o.util.QRCodeUtil;
import com.watermelon.o2o.util.baidu.ShortNetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/12/7 17:22
 */
@Controller
@RequestMapping(value = "fronted")
public class ProductDetailController {

    @Autowired
    private ProductService productService;

    private static String URLPREFIX = "https://open.weixin.qq.com/connect/oauth2/authorize?"
            + "appid=wxd7f6c5b8899fba83&"
            + "redirect_uri=115.28.159.6/myo2o/shop/adduserproductmap&"
            + "response_type=code&scope=snsapi_userinfo&state=";
    private static String URLSUFFIX = "#wechat_redirect";

    @GetMapping(value = "listproductdetailpageinfo")
    @ResponseBody
    private Map<String, Object> listProductDetailPageInfo(
            HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        long productId = HttpServletRequestUtil.getLong(request, "productId");
        Product product;
        if (productId != -1) {
            product = productService.getProductById(productId);
            modelMap.put("product", product);
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty productId");
        }
        return modelMap;
    }

    @GetMapping(value = "generateqrcode4product")
    @ResponseBody
    private void generateQRCode4Product(HttpServletRequest request,
                                        HttpServletResponse response) {
        long productId = HttpServletRequestUtil.getLong(request, "productId");
        PersonInfo user = (PersonInfo) request.getSession()
                .getAttribute("user");
        if (productId != -1 && user != null && user.getUserId() != null) {
            long timpStamp = System.currentTimeMillis();
            String content = "{\"productId\":" + productId + ",\"customerId\":"
                    + user.getUserId() + ",\"createTime\":" + timpStamp + "}";
            String longUrl = URLPREFIX + content + URLSUFFIX;
            String shortUrl = ShortNetAddress.getShortURL(longUrl);
            BitMatrix qRcodeImg = QRCodeUtil.generateQRCodeStream(shortUrl,
                    response);
            try {
                MatrixToImageWriter.writeToStream(qRcodeImg, "png",
                        response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
