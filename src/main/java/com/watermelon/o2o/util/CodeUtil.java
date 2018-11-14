package com.watermelon.o2o.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/11/14 15:06
 */
public class CodeUtil {

    /**
     *
     * @param request
     * @return
     */
    public static boolean checkVerifyCode(HttpServletRequest request) {
        String verifyCodeExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

        String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");

        return verifyCodeActual != null && verifyCodeActual.equalsIgnoreCase(verifyCodeExpected);
    }
}
