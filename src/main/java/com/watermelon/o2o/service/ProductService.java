package com.watermelon.o2o.service;

import com.watermelon.o2o.dto.ImageHolder;
import com.watermelon.o2o.dto.ProductExecution;
import com.watermelon.o2o.entity.Product;
import com.watermelon.o2o.exceptions.ProductOperationException;

import java.io.InputStream;
import java.util.List;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/11/22 14:44
 */
public interface ProductService {

    /**
     * 添加商品信息以及图片处理
     *
     * @param product
     * @param thumbnail
     * @param productImgHolderList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException;


}
