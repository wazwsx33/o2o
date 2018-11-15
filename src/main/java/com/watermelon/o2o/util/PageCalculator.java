package com.watermelon.o2o.util;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/11/15 21:16
 */
public class PageCalculator {

    /**
     * 页数向行数转换函数，用于分页
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public static int calculateRowIndex(int pageIndex, int pageSize) {
        return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
    }
}
