package com.check.security.config;

import com.alibaba.fastjson.JSON;
import com.check.common.util.LogUtils;
import com.check.common.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzc
 */
@Configuration
@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Map<String,Object> ret = new HashMap<>(4);
        ret.put("code",403);
        ret.put("message","用户名或密码错误");
        ret.put("data",null);
        ret.put("date", new Date().toString());
        log.error(LogUtils.getErrorLog(RequestUtils.getIp(request), RequestUtils.getUrl(request),
                "403", "用户名或密码错误"));
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(ret));
    }
}
