package com.click_check.security.controller;

import com.click_check.security.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzc
 */
@RequestMapping
@RestController
public class LoginController {

    @Autowired
    private SysUserMapper userMapper;


}
