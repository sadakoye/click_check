package com.check.security.config;

import com.alibaba.fastjson.JSON;
import com.check.security.pojo.bean.User;
import com.check.security.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Autowired
    private JWTUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //String name = authentication.getName();
        //生成token
        //String token = jwtUtils.generateToken(name);

        //将生成的authentication放入容器中，生成安全的上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = jwtUtils.getUser();
        user.setPassword(null);
        user.setUserId(null);
        user.setCode(null);
        Map<String, Object> ret = new HashMap<>(4);
        ret.put("code", 200);
        ret.put("message", "登录成功");
        ret.put("user", user);
        ret.put("date", new Date().toString());
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(ret));
    }
}
