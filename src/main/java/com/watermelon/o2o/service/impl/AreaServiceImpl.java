package com.watermelon.o2o.service.impl;

import com.watermelon.o2o.dao.AreaDao;
import com.watermelon.o2o.entity.Area;
import com.watermelon.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: AreaService 实现类
 * @Author; Watermelon
 * @Date: 2018/11/7 20:35
 */

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
