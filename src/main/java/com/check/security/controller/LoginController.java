package com.check.security.controller;

import com.check.common.constant.ConstantString;
import com.check.common.pojo.bean.Result;
import com.check.common.util.RedisUtils;
import com.check.security.pojo.bean.User;
import com.check.security.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zzc
 */
@RequestMapping
@RestController
@Api(tags = "登录")
public class LoginController {

    @Resource
    JwtUtils jwtUtils;

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
