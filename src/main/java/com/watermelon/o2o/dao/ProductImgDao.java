package com.watermelon.o2o.dao;

import com.watermelon.o2o.entity.ProductImg;

import java.util.List;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/11/21 16:02
 */
public interface ProductImgDao {

    List<ProductImg> queryProductImgList(long productId);

    /**
     * 批量添加商品详情图片
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    int deleteProductImg(long productId);
}
