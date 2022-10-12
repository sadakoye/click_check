package com.clickAndCheck.config;

import com.clickAndCheck.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author zzc
 */
//@Component
//public class TokenFilter extends GenericFilterBean {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        String token = this.resolveToken(httpServletRequest);
//        if (EmptyUtil.isNotEmpty(token)) {
//            // 解析token获取username
//            String username = new String(Base64.getDecoder().decode(token.getBytes(StandardCharsets.UTF_8)));
//            // 根据token获取鉴权信息
//            UserDetails userDetails = this.userService.loadUserByUsername(username.split("_")[0]);
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    /**
//     * 获取Token
//     *
//     * @param request /
//     * @return /
//     */
//    private String resolveToken(HttpServletRequest request) {
//        return request.getHeader("Authorization");
//    }
//}

