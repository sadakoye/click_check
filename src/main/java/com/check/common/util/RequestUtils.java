package com.check.common.util;

import com.alibaba.druid.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zzc
 */
public class RequestUtils {

    private final static String DOT = ".";

    public static String getIp() {
        HttpServletRequest request = getRequest();
        if (request != null) {
            return getIp();
        }else {
            return null;
        }
    }

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

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null){
            return servletRequestAttributes.getResponse();
        }
        return null;
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null){
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

}
