package com.watermelon.o2o.enums;

/**
 * @Description: 商铺 枚举类
 * @Author; Watermelon
 * @Date: 2018/11/12 20:15
 */
public enum  ShopStateEnum {
    CHECK(0, "审核中"), OFFLINE(-1, "非法店铺"), SUCCESS(1, "操作成功"),
    PASS(2, "通过认证"), INNER_ERROR(-1001, "内部系统错误"), NULL_SHOPID(-1002, "ShopId为空"), NULL_SHOP(-1003, "shop信息为空");

    private int state;
    private String stateInfo;

    ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * 依据传入的state返回响应的enum值
     *
     * @param state
     * @return
     */
    public static ShopStateEnum stateOf(int state) {
        for (ShopStateEnum stateEnum : values()) {
            if (stateEnum.getState() == state)
                return stateEnum;
        }

        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
