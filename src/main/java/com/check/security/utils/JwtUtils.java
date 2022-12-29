package com.check.security.utils;

import com.check.security.config.JwtProperties;
import com.check.security.pojo.bean.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzc
 */
@Component
public class JwtUtils {

    @Resource
    private JwtProperties jwtProperties;

    /**
     * 生成token
     * @param name 用户名称
     * @return String
     */
    public String generateToken(String name){
        Map<String,Object> map = new HashMap<>(1);
        map.put("username",name);
        ///密码
        //map.put("password",user.getPassword());

        return Jwts.builder()
                //设置用户信息
                .setClaims(map)
                //token过期时间
                //.setExpiration(new Date(System.currentTimeMillis()+jwtProperties.getTokenValidityInSeconds()))
                //设置主题
                .setSubject("Wsl_system")
                //设置签名
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getBase64Secret().getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    /**
     * 解析token
     * @param token token
     */
    public String analysisToken(String token){
        if (StringUtils.isNotBlank(token)) {
            Claims body = Jwts.parser()
                    .setSigningKey(jwtProperties.getBase64Secret().getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody();

            return body.get("username").toString();
        }
        return null;
    }

    /**
     * 获取user
     */
    public User getUser(){
           SecurityContext context = SecurityContextHolder.getContext();
           Authentication authentication = context.getAuthentication();
        User user = new User();
        try {
            if (authentication.getPrincipal() instanceof User) {
                user = (User) authentication.getPrincipal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}