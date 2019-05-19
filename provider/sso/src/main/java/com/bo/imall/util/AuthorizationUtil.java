package com.bo.imall.util;

import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class AuthorizationUtil {

    private static final String AUTHORIZATION = "Authorization";

    public static String getSessionId(ServletRequest request) {
        return WebUtils.toHttp(request).getHeader(AUTHORIZATION);
    }

    public static String getSessionId(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }
}
