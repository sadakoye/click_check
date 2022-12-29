package com.check.security.controller;

import com.check.common.constant.ConstantString;
import com.check.common.pojo.bean.Result;
import com.check.common.util.RedisUtils;
import com.check.security.config.JwtAuthenticationTokenFilter;
import com.check.security.config.JwtUserServiceImpl;
import com.check.security.pojo.bean.User;
import com.check.security.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
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
@RequestMapping
@RestController
@Api(tags = "登录")
public class LoginController {

    @Resource
    JwtUtils jwtUtils;

    @Resource
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 登录
     *
     * @return Result
     * @author zzc
     */
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result<Object> login() {
        return Result.success();
    }

    /**
     * 登录
     *
     * @return Result
     * @author zzc
     */
    @ApiOperation(value = "登录")
    @GetMapping("/login")
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
//        try {
//            jwtAuthenticationTokenFilter.doFilterInternal(request, response, filterChain);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        User user = jwtUtils.getUser();
//        Map<String, Object> ret = new HashMap<>(4);
//        ret.put("code", 200);
//        ret.put("message", "登录成功");
//        ret.put("user", user);
//        ret.put("date", new Date().toString());
//        return ret;
        return null;
    }

    /**
     * 登出
     *
     * @return Result
     * @author zzc
     */
    @ApiOperation(value = "登出")
    @PostMapping("/logout")
    public Result<Object> logout() {
        User user = jwtUtils.getUser();
        if (RedisUtils.deleteValue(ConstantString.REDIS_USER + user.getUsername())) {
            return Result.success();
        }
        return Result.error("未登录或已登出");
    }

}
