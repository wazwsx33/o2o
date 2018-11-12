package com.watermelon.o2o.util;

/**
 * @Description: 路径 工具类
 * @Author; Watermelon
 * @Date: 2018/11/12 16:34
 */
public class PathUtil {

    private static String separator = System.getProperty("file.separator");

    /**
     * 获取图片的根路径
     * @return
     */
    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "F:/projectdev/image/";
        } else {
            basePath = "/home/watermelon/image/";
        }
        basePath = basePath.replace("/", separator);

        return basePath;
    }

    /**
     * 根据商铺ID获取图片子路径
     * @param shopId
     * @return
     */
    public static String getShopImagePath(long shopId) {
        String imagePah = "upload/item/shop/" + shopId + "/";

        return imagePah.replace("/", separator);
    }
}
