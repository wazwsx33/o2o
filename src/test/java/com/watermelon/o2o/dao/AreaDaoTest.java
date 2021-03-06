package com.watermelon.o2o.dao;

import com.watermelon.o2o.BaseTest;
import com.watermelon.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Description: AreaDao 测试类
 * @Author; Watermelon
 * @Date: 2018/11/7 20:22
 */
public class AreaDaoTest extends BaseTest {

    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea() {
        List<Area> areaList = areaDao.queryArea();
        assertEquals(2, areaList.size());
    }
}
