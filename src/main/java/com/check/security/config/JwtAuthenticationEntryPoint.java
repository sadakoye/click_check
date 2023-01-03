package com.check.security.config;

import com.alibaba.fastjson.JSON;
import com.check.common.pojo.bean.Result;
import com.check.common.util.LogUtils;
import com.check.common.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zzc
 */
@Configuration
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error(LogUtils.getErrorLog(RequestUtils.getIp(request), RequestUtils.getUrl(request),
                "401", "无效token"));
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.error(401, "token无效或过期")));
    }
}