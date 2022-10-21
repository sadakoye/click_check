package com.clickAndCheck.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/get")
    public String  get(){
        return "SUCCESS";
    }

    @GetMapping("/delete")
    //需要admin角色
    @PreAuthorize("hasAnyRole('admin')")
    public String delete(){
        return "操作成功";
    }


    @PostMapping("/add")
    //需要add权限
    @PreAuthorize("hasAnyAuthority('add')")
    public String add(){
        return "操作成功";
    }

}