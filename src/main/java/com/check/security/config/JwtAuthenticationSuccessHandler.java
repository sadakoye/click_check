package com.check.security.config;

import com.alibaba.fastjson.JSON;
import com.check.common.pojo.bean.Result;
import com.check.common.util.LogUtils;
import com.check.common.util.RequestUtils;
import com.check.security.pojo.bean.User;
import com.check.security.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zzc
 */
@Configuration
@Slf4j
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ///旧生成token
        // String name = authentication.getName();
        // 生成token
        // String token = jwtUtils.generateToken(name);

        //将生成的authentication放入容器中，生成安全的上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = jwtUtils.getUser();
        user.setPassword(null);
        user.setId(null);
        user.setCode(null);
        user.setIdCard(null);
        log.info(LogUtils.getInfoLog(RequestUtils.getIp(httpServletRequest), RequestUtils.getUrl(httpServletRequest),
                "200", "登录成功", user.getUsername()));
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Content-Type");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");

        httpServletResponse.getWriter().write(JSON.toJSONString(Result.success("登录成功", user)));
    }
}
