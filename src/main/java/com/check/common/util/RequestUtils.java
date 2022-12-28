package com.check.common.util;

import com.alibaba.druid.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zzc
 */
public class RequestUtils {

    private final static String DOT = ".";

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-Ip");
        if (StringUtils.isEmpty(ip) || !ip.contains(DOT)) {
            ip = request.getHeader("X-Forwarded-For");
            if (StringUtils.isEmpty(ip) || !ip.contains(DOT)) {
                ip = request.getRemoteAddr();
            }
        }
        return ip;
    }

    public static String getUrl(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

}
