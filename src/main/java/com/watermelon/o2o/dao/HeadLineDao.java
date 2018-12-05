package com.watermelon.o2o.dao;

import com.watermelon.o2o.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/11/30 16:08
 */
public interface HeadLineDao {

    /**
     * 根据传入的查询条件（头条名查询头条）
     * @param headLineCondition
     * @return
     */
    List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);


}
