package com.watermelon.o2o.service;

import com.watermelon.o2o.entity.HeadLine;

import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/11/30 16:24
 */
public interface HeadLineService {

    /**
     * 根据传入的条件返回指定的头条列表
     *
     * @param headLineCondition
     * @return
     * @throws IOException
     */
    List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
}
