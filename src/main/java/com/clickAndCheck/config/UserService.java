package com.clickAndCheck.config;

import com.clickAndCheck.mapper.SysMenuMapper;
import com.clickAndCheck.mapper.SysRoleMapper;
import com.clickAndCheck.mapper.SysUserMapper;
import com.clickAndCheck.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

///**
// * 2、定义实现UserDetailsService接口的类,并重写loadUserByUsername方法,在此方法中根据用户名获取用户信息
// * 返回的用户信息也是spring security专用的用户类,也就是.config.User
// *
// * @author zzc
// */
//@Service
//public class UserService implements UserDetailsService {
//
//    @Autowired
//    private SysUserMapper userMapper;
//
//    @Autowired
//    private SysRoleMapper roleMapper;
//
//    @Autowired
//    private SysMenuMapper menuMapper;
//
//    /**
//     * 用户信息缓存
//     */
//    static Map<String, User> userCache = new ConcurrentHashMap<>();
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user;
//        // 判断缓存中是否包含当前用户
//        if (userCache.containsKey(username)) {
//            user = userCache.get(username);
//        } else {
//            // 用户基本信息
//            user = this.userMapper.getSecurityUser(username);
//            if (EmptyUtil.isNotEmpty(user)) {
//                // 用户角色列表
//                user.setRoles(roleMapper.getUserRoles(user.getUserId()));
//                // 用户菜单列表
//                user.setMenus(menuMapper.getUserMenus(user.getUserId()));
//                // token
//                String token = Base64.getEncoder().encodeToString((user.getUsername() + "_" + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8));
//                user.setToken(token);
//                // 将用户信息保存在缓存中
//                userCache.put(username, user);
//            }
//        }
//        return user;
//    }
//}

