package com.watermelon.o2o.controller.superAdmin;

import com.watermelon.o2o.entity.Area;
import com.watermelon.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: Area 视图层
 * @Author; Watermelon
 * @Date: 2018/11/7 20:43
 */

@Controller
@RequestMapping("/superAdmin")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping(value = "/listarea")
    @ResponseBody
    private Map<String, Object> listArea() {
        Map<String, Object> modelMap = new HashMap<>();
        List<Area> list;

        try {
            list = areaService.getAreaList();
            modelMap.put("rows", list);
            modelMap.put("total", list.size());
        } catch (Exception e){
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }

        return modelMap;
    }
}
