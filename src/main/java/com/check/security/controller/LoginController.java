package com.check.security.controller;

import com.check.common.pojo.bean.Result;
import com.check.security.pojo.bean.User;
import com.check.security.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zzc
 */
@RequestMapping
@RestController
@Api(tags = "登录")
public class LoginController {

    @Resource
    LoginService service;

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
     * @param request     request
     * @param response    response
     * @param filterChain filterChain
     * @return Result<User>
     * @author zzc
     */
    @ApiOperation(value = "登录")
    @GetMapping("/login")
    public Result<User> login(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        return service.login(request, response, filterChain);
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
        return service.logout();
    }

    /**
     * CAS-门户登录
     *
     * @param ticket   票据
     * @param request  request
     * @param response response
     * @return String
     * @author zzc
     */
    @ApiOperation(value = "CAS-门户登录")
    @RequestMapping("/cas/login")
    public Result<Object> casLogin(String ticket,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        return service.casLogin(ticket, request, response);
    }

    /**
     * CAS-从门户系统同步用户
     *
     * @param request request
     * @return String
     * @author zzc
     */
    @ApiOperation(value = "CAS-从门户系统同步用户")
    @RequestMapping("/casAutoUser")
    public Result<Object> casAutoUser(HttpServletRequest request) {
        return service.casAutoUser(request);
    }

}
