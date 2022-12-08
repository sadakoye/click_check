package com.check.security.config;

import com.check.common.exception.CommonException;
import com.check.common.constant.ConstantString;
import com.check.common.util.RedisUtils;
import com.check.security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zzc
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private JwtUserServiceImpl jwtUserService;

    @Resource
    private JwtProperties jwtProperties;

    @Resource
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader(jwtProperties.getHeader());
        //解析token
        try {
            String name = jwtUtils.analysisToken(token);
            //当token中的username不为空时进行验证token是否是有效的token
            if (name != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                if (RedisUtils.contain(ConstantString.REDIS_USER + name)) {
                    RedisUtils.expire(ConstantString.REDIS_USER + name, jwtProperties.getTokenValidityInSeconds(), TimeUnit.MINUTES);
                } else {
                    throw new CommonException(401, "token无效或过期");
                }

                UserDetails userDetails = jwtUserService.loadUserByUsername(name);
                //认证
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            filterChain.doFilter(request, response);
        }
    }
}