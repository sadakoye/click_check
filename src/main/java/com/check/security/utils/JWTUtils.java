package com.check.security.utils;

import com.check.common.util.RedisUtils;
import com.check.security.config.JwtProperties;
import com.check.security.config.User;
import com.check.security.mapper.SysUserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @Resource
    private SysUserMapper userMapper;

    /**
     * 生成token
     * @param name 用户名称
     * @return
     */
    public String generateToken(String name){
        Map<String,Object> map = new HashMap<>();
        map.put("username",name);
        //map.put("password",user.getPassword());
        User securityUser = userMapper.getSecurityUser(name);
        String token = Jwts.builder()
                //设置用户信息
                .setClaims(map)
                //token过期时间
                //.setExpiration(new Date(System.currentTimeMillis()+jwtProperties.getTokenValidityInSeconds()))
                //设置主题
                .setSubject("Wsl_system")
                //设置签名
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getBase64Secret().getBytes(StandardCharsets.UTF_8))
                .compact();

        //RedisUtils.saveValue(name, )
        return token;
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
     * 获取userName
     */
    public String getUserName(){
        ServletRequestAttributes servletRequestAttributes =  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
       if (servletRequestAttributes != null) {
           HttpServletRequest request = servletRequestAttributes.getRequest();
           String token = request.getHeader(jwtProperties.getHeader());
           return analysisToken(token);
       }
       return null;
    }

}