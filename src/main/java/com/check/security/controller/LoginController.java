package com.check.security.controller;

import com.check.system.mapper.SysUserMapper;
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
