package com.check.security.service;

import com.check.common.pojo.bean.Result;
import com.check.security.pojo.bean.User;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zzc
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param request     request
     * @param response    response
     * @param filterChain filterChain
     * @return Result<User>
     * @author zzc
     */
    Result<User> login(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain);

    /**
     * 登出
     *
     * @return Result
     * @author zzc
     */
    Result<Object> logout();

    /**
     * CAS-门户登录
     *
     * @param ticket   票据
     * @param request  request
     * @param response response
     * @return String
     * @author zzc
     */
    Result<Object> casLogin(String ticket, HttpServletRequest request, HttpServletResponse response);

    /**
     * CAS-从门户系统同步用户
     *
     * @param request request
     * @return String
     * @author zzc
     */
    Result<Object> casAutoUser(HttpServletRequest request);
}

