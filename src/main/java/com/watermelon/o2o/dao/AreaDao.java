package com.watermelon.o2o.dao;

import com.watermelon.o2o.entity.Area;

import java.util.List;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/11/7 20:08
 */
public interface AreaDao {

    /**
     * 列出区域列表
     * @return areaList
     */
    List<Area> queryArea();
}
