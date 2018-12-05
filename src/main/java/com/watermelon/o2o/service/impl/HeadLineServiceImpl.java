package com.watermelon.o2o.service.impl;

import com.watermelon.o2o.dao.HeadLineDao;
import com.watermelon.o2o.entity.HeadLine;
import com.watermelon.o2o.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/11/30 16:25
 */
@Service
public class HeadLineServiceImpl implements HeadLineService {

    @Autowired
    private HeadLineDao headLineDao;

    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
        return headLineDao.queryHeadLine(headLineCondition);
    }
}
