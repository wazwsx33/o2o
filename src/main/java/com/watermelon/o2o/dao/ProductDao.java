package com.watermelon.o2o.dao;

import com.watermelon.o2o.entity.Product;
import com.watermelon.o2o.entity.ProductImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 商品 接口类
 * @Author; Watermelon
 * @Date: 2018/11/21 15:55
 */
public interface ProductDao {

    /**
     * 查询商品累表并分页，课输入的条件有：商品名（模糊），商品状态，店铺ID，商品类别
     *
     * @param productCondition
     * @param pageSize
     * @return
     */
    List<Product> queryProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * 查询商品总数
     *
     * @param productCondition
     * @return
     */
    int queryProductCount(@Param("productCondition") Product productCondition);

    /**
     * 插入商品
     *
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     * 通过ProductId查询卫衣的商品信息
     *
     * @param productId
     * @return
     */
    Product queryProductById(long productId);

    /**
     * 修改商品信息
     *
     * @param product
     * @return
     */
    int updateProduct(Product product);
}
