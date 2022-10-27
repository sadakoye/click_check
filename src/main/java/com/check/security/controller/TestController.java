package com.check.security.controller;

import com.check.common.pojo.bean.Result;
import com.check.security.config.User;
import com.check.security.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JWTUtils jwtUtils;

    @GetMapping("/get")
    public String get() {
        return "SUCCESS";
    }

    @GetMapping("/delete")
    //需要admin角色
    @PreAuthorize("hasAnyAuthority('MENU_admin')")
    public Result delete() {
//        User user = jwtUtils.getUserName();
//        return Result.success(user);
        return Result.success();
    }

    @PostMapping("/add")
    //需要add权限
    @PreAuthorize("hasAnyAuthority('add')")
    public String add() {
        return "操作成功";
    }

}