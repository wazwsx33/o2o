package com.watermelon.o2o.service;

import com.watermelon.o2o.BaseTest;
import com.watermelon.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Description: AreaService 测试类
 * @Author; Watermelon
 * @Date: 2018/11/7 20:38
 */

public class AreaServiceTest extends BaseTest {

    @Autowired
    private AreaService areaService;

    @Test
    public void testGetAreaList() {
        List<Area> areaList = areaService.getAreaList();

        assertEquals("西苑", areaList.get(0).getAreaName());
    }
}
