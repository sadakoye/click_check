package com.clickAndCheck.controller;

import com.clickAndCheck.config.JwtUserServiceImpl;
import com.clickAndCheck.config.User;
import com.clickAndCheck.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/login")
    public void login(String name, String password){
        User user = userMapper.getSecurityUser(name);
        if (!user.getPassword().equals(password)){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
    }

}
