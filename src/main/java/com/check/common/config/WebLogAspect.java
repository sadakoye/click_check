package com.check.common.config;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.check.common.util.RequestUtils;
import com.check.security.pojo.bean.User;
import com.check.security.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Administrator
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    private final static int MAX_LENGTH = 1000;
    private final static String LINE_SEPARATOR = System.getProperty("line.separator");
    private final static String DOT = ".";

    @Resource
    private JwtUtils jwtUtils;

    @Pointcut("execution(public * com.check..*.*Controller.*(..))")
    public void controllerMethodAop() {
    }

    @Around("controllerMethodAop()")
    public Object logRequestBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder logBuffer = new StringBuilder(64);
        logBuffer.append(LINE_SEPARATOR);
        appendContentLog(logBuffer, joinPoint);
        appendUserLog(logBuffer);
        Object result = appendResultLog(logBuffer, joinPoint);
        log.info(logBuffer.toString());
        return result;
    }

    /**
     * 添加登录人信息到日志字符串变量
     *
     * @param logBuffer logBuffer
     */
    private void appendUserLog(StringBuilder logBuffer) {
        User user = jwtUtils.getUser();
        if (user.getUsername() != null) {
            logBuffer.append("+  USER: ").append(user.getUsername()).append(LINE_SEPARATOR);
        } else {
            logBuffer.append("+  USER: NOT").append(LINE_SEPARATOR);
        }
    }

    /**
     * 添加主要内容到日志字符串变量
     *
     * @param logBuffer 日志字符串
     * @param joinPoint joinPoint
     */
    private void appendContentLog(StringBuilder logBuffer, ProceedingJoinPoint joinPoint) {
        // 记录请求内容
        logBuffer.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++").append(LINE_SEPARATOR);
        logBuffer.append("+  TIMESTAMP: ").append(new Date()).append(LINE_SEPARATOR);
        logBuffer.append("+  THREAD: ").append(Thread.currentThread().getName()).append(LINE_SEPARATOR);

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            String ip = RequestUtils.getIp(request, DOT);
            String url = request.getRequestURL().toString();
            String params = Arrays.toString(joinPoint.getArgs());
            logBuffer.append("+  IP: ").append(ip).append(LINE_SEPARATOR)
                    .append("+  URL: ").append(url).append(LINE_SEPARATOR)
                    .append("+  HTTP_METHOD: ").append(request.getMethod()).append(LINE_SEPARATOR)
                    .append("+  THE ARGS OF THE METHOD: ").append(params).append(LINE_SEPARATOR)
                    .append("+  CLASS_METHOD: ").append(joinPoint.getSignature().getDeclaringTypeName()).append(".").append(joinPoint.getSignature().getName()).append(LINE_SEPARATOR);
        }
    }

    /**
     * 接口结果拼接到日志字符串变量
     *
     * @param logBuffer 日志字符串
     * @param joinPoint joinPoint
     * @return Object
     */
    private Object appendResultLog(StringBuilder logBuffer, ProceedingJoinPoint joinPoint) throws Throwable {
        Long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        if (result != null) {
            String rsStr = JSONObject.toJSONString(result);
            if (!StringUtils.isEmpty(rsStr) && rsStr.length() > MAX_LENGTH) {
                rsStr = rsStr.substring(0, MAX_LENGTH);
                logBuffer.append("+  RESULT: ").append(rsStr).append(LINE_SEPARATOR);
            }
        }
        Long end = System.currentTimeMillis();
        Long costTime = end - begin;
        logBuffer.append("+  COST_TIME: ").append(costTime).append(LINE_SEPARATOR);
        logBuffer.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++").append(LINE_SEPARATOR);
        return result;
    }
}


