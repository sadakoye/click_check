package com.clickAndCheck.security.utils;

import com.clickAndCheck.security.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzc
 */
@Component
public class JWTUtils {

    @Resource
    private JwtProperties jwtProperties;


    /**
     * 生成token
     * @param name 用户名称
     * @return
     */
    public  String generateToken(String name){
        Map<String,Object> map = new HashMap<>();
        map.put("username",name);
//        map.put("password",user.getPassword());
        return Jwts.builder()
                //设置用户信息
                .setClaims(map)
                //token过期时间
                .setExpiration(new Date(System.currentTimeMillis()+jwtProperties.getTokenValidityInSeconds()))
                //设置主题
                .setSubject("Wsl_system")
                //设置签名
                .signWith(SignatureAlgorithm.HS512,jwtProperties.getBase64Secret().getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    /**
     * 解析token
     * @param token token
     */
    public   String analysisToken(String token){
        Claims body = Jwts.parser()
                .setSigningKey(jwtProperties.getBase64Secret().getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();

        return body.get("username").toString();
    }


}