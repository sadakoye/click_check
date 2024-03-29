package com.check.security.controller;

import com.check.common.pojo.bean.Result;
import com.check.common.util.RedisUtils;
import com.check.security.pojo.bean.User;
import com.check.security.utils.JwtUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 测试
 *
 * @author zzc
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @Resource
    private JwtUtils jwtUtils;

    /**
     * 需要权限的测试方法
     *
     * @return Result
     * @author zzc
     */
    @GetMapping("/get")
    public Result<Object> get() {
        Object userAdmin = RedisUtils.getValue("user_admin");

        User user = (User) userAdmin;

        return Result.success(user);
    }

    /**
     * 需要权限的测试方法
     *
     * @return Result
     * @author zzc
     */
    @PostMapping("/getUser")
    @PreAuthorize("hasAnyAuthority('Menu') OR hasAnyAuthority('Admin')")
    public Result<Object> delete() {
        return Result.success(jwtUtils.getUser());
    }

    /**
     * 需要权限的测试方法
     *
     * @return Result
     * @author zzc
     */
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('add')")
    public Result<String> add() {
        return Result.success("操作成功");
    }

    /**
     * 跳转测试
     *
     * @return String
     * @author zzc
     */
    @GetMapping("/redirect")
    public String redirect(HttpServletResponse response) {
        try {
            response.sendRedirect("https://www.baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:www.baidu.com";
    }

}